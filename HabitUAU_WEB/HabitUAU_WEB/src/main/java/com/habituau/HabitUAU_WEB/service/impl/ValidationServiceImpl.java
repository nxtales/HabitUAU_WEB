//ESSA CLASSE NAO FUNCIONA
//AQUI SERÁ A VALIDAÇÃO DAS IMAGENS VIA API



package com.habituau.HabitUAU_WEB.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.habituau.HabitUAU_WEB.model.entity.DesafioInscritoTarefaCompleta;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.service.ValidationService;

/*@Service
public class ValidationServiceImpl implements ValidationService {

    private final DesafioInscritosRepository desafioInscritosRepository;
    private final DesafioInscritosTarefasCompletasRepository desafioInscritosTarefasCompletasRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ValidationServiceImpl(DesafioInscritosRepository desafioInscritosRepository,
                                 DesafioInscritosTarefasCompletasRepository desafioInscritosTarefasCompletasRepository,
                                 RestTemplate restTemplate) {
        this.desafioInscritosRepository = desafioInscritosRepository;
        this.desafioInscritosTarefasCompletasRepository = desafioInscritosTarefasCompletasRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void validateChallenge(Long cpf, Long idTarefa, Long desafioId, String textoComparacao, String urlImagem) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        // Chama a API para descrever a imagem
        String descricaoImagem = callImageDescriptionAPI(urlImagem);

        // Compara a descrição da imagem com o texto passado pelo usuário
        if (descricaoImagem != null && descricaoImagem.contains(textoComparacao)) {
            // Registra na tabela DESAFIO_INSCRITOS_TAREFAS_COMPLETAS
            DesafioInscritoTarefaCompleta tarefaCompleta = new DesafioInscritoTarefaCompleta();
//            tarefaCompleta.setCliente(/* buscar cliente pelo CPF *///);
//           tarefaCompleta.setTarefa(/* buscar tarefa pelo idTarefa */);
  //          tarefaCompleta.setDesafio(/* buscar desafio pelo desafioId */);
 //           tarefaCompleta.setSumPontos(/* definir pontos conforme a lógica do seu sistema */);
            
/*            desafioInscritosTarefasCompletasRepository.save(tarefaCompleta);
        } else {
            throw new RuntimeException("A descrição da imagem não corresponde ao texto fornecido");
        }
    }

    private String callImageDescriptionAPI(String urlImagem) {
        try {
            return restTemplate.getForObject(urlImagem, String.class); // Ajuste conforme a API
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API de descrição da imagem: " + e.getMessage());
        }
    }

    @Override
    public boolean isChallengeValidated(Long cpf, Long idTarefa, Long desafioId) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        return desafioInscritosTarefasCompletasRepository
                .findByCpfClienteAndIdTarefaAndIdDesafio(cpf.toString(), idTarefa, desafioId)
                .isPresent();
    }

    // Validação de CPF
    private boolean isValidCPF(Long cpf) {
        String cpfString = String.valueOf(cpf);
        return cpfString.matches("\\d{11}");
    }
}*/
