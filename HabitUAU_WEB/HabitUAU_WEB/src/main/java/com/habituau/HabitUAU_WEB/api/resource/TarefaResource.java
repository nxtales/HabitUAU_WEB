package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.TarefaDTO;
import com.habituau.HabitUAU_WEB.model.entity.*;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioTarefasRepository;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
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
    private final String VISION_API_KEY = "Bs120Koy6F0QZu1Tj43DFNHms0xVWDHm4ZHQ01ia16bmAx5ImYwrJQQJ99AKACYeBjFXJ3w3AAAFACOGYSxT";

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
    
 // Endpoint para validar e registrar tarefa como completa
    @PostMapping("/validateAndComplete")
    public ResponseEntity<?> validateAndCompleteTarefa(
            @RequestParam Long tarefaId,
            @RequestParam String cpfCliente,
            @RequestParam MultipartFile image
    ) {
        Optional<DesafioTarefa> tarefaOpt = tarefasRepository.findById(tarefaId);
        if (tarefaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Tarefa não encontrada para o ID fornecido.");
        }

        DesafioTarefa tarefa = tarefaOpt.get();

        Optional<Cliente> clienteOpt = clienteRepository.findByCPF(cpfCliente);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente não encontrado para o CPF fornecido.");
        }

        Cliente cliente = clienteOpt.get();

        try {
            List<String> descriptions = analyzeImage(image);
            if (descriptions.isEmpty()) {
                return ResponseEntity.badRequest().body("Não foi possível analisar a imagem.");
            }

            // Imprime todas as descrições recebidas
            System.out.println("Descrições retornadas pela Azure Vision: " + descriptions);

            // Validação ajustada
            boolean isSimilar = descriptions.stream()
                    .anyMatch(description -> isContextuallySimilar(tarefa.getNome_tarefa(), description));

            if (!isSimilar) {
                return ResponseEntity.badRequest().body("A descrição da imagem não é compatível com o nome da tarefa. Descrições retornadas: " + descriptions);
            }

            // Registrar a tarefa como concluída
            DesafioInscritoTarefaCompleta tarefaCompleta = new DesafioInscritoTarefaCompleta();
            tarefaCompleta.setCliente(cliente);
            tarefaCompleta.setTarefa(tarefa);
            tarefaCompleta.setDesafio(tarefa.getDesafio());
            tarefaCompleta.setSumPontos(tarefa.getqtde_pontos().intValue());

            completasRepository.save(tarefaCompleta);

            return ResponseEntity.ok("Tarefa completada com sucesso! Descrições analisadas: " + descriptions);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao processar a imagem: " + e.getMessage());
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
                return responseBody.get("description").get("captions")
                        .findValuesAsText("text");
            }
        }
        return Collections.emptyList();
    }

    private boolean isContextuallySimilar(String taskName, String description) {
        // Normalização dos textos
        String normalizedTaskName = normalizeText(taskName);
        String normalizedDescription = normalizeText(description);

        // Verifica similaridade de cosseno
        boolean cosineSimilar = isTextSimilar(normalizedTaskName, normalizedDescription);

        // Verifica correspondência de palavras-chave
        boolean keywordMatch = containsImportantWords(normalizedTaskName, normalizedDescription);

        // Combina os critérios
        return cosineSimilar || keywordMatch;
    }

    private String normalizeText(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "") // Remove caracteres especiais
                .trim();
    }

    private boolean containsImportantWords(String text1, String text2) {
        Set<String> words1 = new HashSet<>(Arrays.asList(text1.split("\\s+")));
        Set<String> words2 = new HashSet<>(Arrays.asList(text2.split("\\s+")));

        words1.retainAll(words2); // Mantém apenas palavras em comum

        return !words1.isEmpty(); // Retorna true se houver interseção
    }

    private boolean isTextSimilar(String text1, String text2) {
        Map<String, Integer> vector1 = buildWordFrequencyVector(text1);
        Map<String, Integer> vector2 = buildWordFrequencyVector(text2);

        Set<String> uniqueWords = new HashSet<>(vector1.keySet());
        uniqueWords.addAll(vector2.keySet());

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (String word : uniqueWords) {
            int count1 = vector1.getOrDefault(word, 0);
            int count2 = vector2.getOrDefault(word, 0);

            dotProduct += count1 * count2;
            norm1 += Math.pow(count1, 2);
            norm2 += Math.pow(count2, 2);
        }

        double similarity = dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
        return similarity > 0.3; // Threshold ajustado
    }

    private Map<String, Integer> buildWordFrequencyVector(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }
}
