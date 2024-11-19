package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.entity.*;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    @Autowired
    private DesafioTarefasRepository tarefasRepository;

    @Autowired
    private DesafioInscritosTarefasCompletasRepository completasRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private final String VISION_ENDPOINT = "https://habituauimageanalyzer.cognitiveservices.azure.com/";
    private final String VISION_API_KEY = "BGiNzKbpzwEpZIEAFynYtHgBbmsQeDKNC9oGr6RJcE0nSY8IWzGBJQQJ99AKACYeBjFXJ3w3AAAFACOGmlnf";

    // Endpoint para listar todas as tarefas
    @GetMapping("/all")
    public ResponseEntity<List<TarefaDTO>> getAllTarefas() {
        List<DesafioTarefa> tarefas = tarefasRepository.findAll();

        List<TarefaDTO> tarefasDTO = tarefas.stream()
                .map(tarefa -> new TarefaDTO(
                        tarefa.getID(),
                        tarefa.getNome_tarefa(),
                        tarefa.getqtde_pontos(),
                        false,
                        tarefa.getDesafio() != null ? tarefa.getDesafio().getId() : null
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tarefasDTO);
    }

 // Endpoint para editar uma tarefa existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<TarefaDTO> editTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(id);

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DesafioTarefa tarefa = tarefaOpt.get();
        tarefa.setNome_tarefa(tarefaDTO.getNome());
        tarefa.setqtde_pontos(tarefaDTO.getQtdepontos());

        // Salva as alterações
        DesafioTarefa updatedTarefa = tarefasRepository.save(tarefa);

        // Converte para DTO e retorna
        TarefaDTO updatedTarefaDTO = new TarefaDTO(
                updatedTarefa.getID(),
                updatedTarefa.getNome_tarefa(),
                updatedTarefa.getqtde_pontos(),
                false, // Completude permanece false
                updatedTarefa.getDesafio() != null ? updatedTarefa.getDesafio().getId() : null
        );

        return ResponseEntity.ok(updatedTarefaDTO);
    }

    // Endpoint para deletar uma tarefa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(id);

        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Deleta a tarefa
        tarefasRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/validateAndComplete")
    public ResponseEntity<?> validateAndCompleteTarefa(
            @RequestParam Long tarefaId,
            @RequestParam String cpfCliente,
            @RequestParam MultipartFile image
    ) {
        // Verifica se a tarefa existe
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(tarefaId);
        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Tarefa não encontrada para o ID fornecido.");
        }

        DesafioTarefa tarefa = tarefaOpt.get();

        // Verifica se o cliente existe
        Optional<Cliente> clienteOpt = clienteRepository.findByCPF(cpfCliente);
        if (clienteOpt.isEmpty()) {
            System.out.println("Cliente não encontrado para o CPF: " + cpfCliente);
            return ResponseEntity.badRequest().body("Cliente não encontrado para o CPF fornecido.");
        }

        Cliente cliente = clienteOpt.get();
        System.out.println("Cliente carregado: Nome - " + cliente.getNome() + ", CPF - " + cliente.getCpf());

        try {
            // Analisa a imagem
            List<String> descriptions = analyzeImage(image);
            if (descriptions.isEmpty()) {
                return ResponseEntity.badRequest().body("Não foi possível analisar a imagem.");
            }

            System.out.println("Descrições retornadas pela análise de imagem: " + descriptions);

            // Traduz o nome da tarefa para inglês
            String translatedTaskName = translateText(tarefa.getNome_tarefa(), "pt", "en");
            System.out.println("Nome da tarefa traduzido para inglês: " + translatedTaskName);

            // Verifica similaridade contextual
            boolean isSimilar = descriptions.stream()
                    .anyMatch(description -> isContextuallySimilar(translatedTaskName, description));

            if (!isSimilar) {
                return ResponseEntity.badRequest().body(
                        "A descrição da imagem não é compatível com o nome da tarefa. Descrições retornadas: " + descriptions
                );
            }

            // Registrar a tarefa como concluída
            DesafioInscritoTarefaCompleta tarefaCompleta = new DesafioInscritoTarefaCompleta();
            tarefaCompleta.setCliente(cliente); // Associa o cliente, que inclui o CPF
            tarefaCompleta.setTarefa(tarefa);
            tarefaCompleta.setDesafio(tarefa.getDesafio());
            tarefaCompleta.setSumPontos(tarefa.getqtde_pontos().intValue());

            System.out.println("Registrando tarefa completa: Cliente CPF - " + cliente.getCpf() +
                    ", Tarefa ID - " + tarefa.getID() +
                    ", Desafio ID - " + (tarefa.getDesafio() != null ? tarefa.getDesafio().getId() : "N/A"));

            completasRepository.save(tarefaCompleta);

            return ResponseEntity.ok("Tarefa completada com sucesso! Descrições retornadas: " + descriptions);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao processar a imagem: " + e.getMessage());
        }
    }


    private String translateText(String text, String fromLanguage, String toLanguage) {
        RestTemplate restTemplate = new RestTemplate();
        String translatorEndpoint = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0";

        // URL com os parâmetros de idioma
        String url = translatorEndpoint + "&from=" + fromLanguage + "&to=" + toLanguage;

        // Configuração dos cabeçalhos
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        String TRANSLATOR_API_KEY = "2aX8LpZbLIZTSexhtyXLt3WRdgxiS3OZ30XBrzPoFzQU0qKsrU1IJQQJ99AKACZoyfiXJ3w3AAAbACOGJdLB";
		headers.set("Ocp-Apim-Subscription-Key", TRANSLATOR_API_KEY );
        headers.set("Content-Type", "application/json");
        headers.set("Ocp-Apim-Subscription-Region", "<region>"); // Adicione a região, se necessário

        // Corpo da solicitação
        List<Map<String, String>> body = Collections.singletonList(Collections.singletonMap("Text", text));
        org.springframework.http.HttpEntity<List<Map<String, String>>> requestEntity = new org.springframework.http.HttpEntity<>(body, headers);

        try {
            // Faz a solicitação POST
            org.springframework.http.ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            // Processa a resposta
            com.fasterxml.jackson.databind.JsonNode responseBody =
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.getBody());

            return responseBody.get(0).get("translations").get(0).get("text").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return text; // Retorna o texto original em caso de falha
        }
    }


    private List<String> analyzeImage(MultipartFile image) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String visionApiUrl = VISION_ENDPOINT + "vision/v3.2/analyze?visualFeatures=Description";

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Ocp-Apim-Subscription-Key", VISION_API_KEY);
        headers.set("Content-Type", "application/octet-stream");

        byte[] imageBytes = image.getBytes();

        org.springframework.http.HttpEntity<byte[]> requestEntity = new org.springframework.http.HttpEntity<>(imageBytes, headers);

        org.springframework.http.ResponseEntity<String> response = restTemplate.postForEntity(visionApiUrl, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            com.fasterxml.jackson.databind.JsonNode responseBody =
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.getBody());

            if (responseBody.has("description") && responseBody.get("description").has("captions")) {
                return responseBody.get("description").get("captions").findValuesAsText("text");
            }
        }
        return Collections.emptyList();
    }

    private boolean isContextuallySimilar(String text1, String text2) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> vector1 = toFrequencyMap(text1.toLowerCase());
        Map<CharSequence, Integer> vector2 = toFrequencyMap(text2.toLowerCase());

        Double similarity = cosineSimilarity.cosineSimilarity(vector1, vector2);
        return similarity != null && similarity > 0.7; // Ajuste o limite de similaridade conforme necessário
    }

    private Map<CharSequence, Integer> toFrequencyMap(String text) {
        Map<CharSequence, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            String key = String.valueOf(c); // Converte char para String
            frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
        }
        return frequencyMap;
    }

}
