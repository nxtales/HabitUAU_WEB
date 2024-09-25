package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesafioRepository extends JpaRepository<Desafio, Long> {

    // Buscar desafios por categoria ou parceiro
    List<Desafio> findByIdCategoriaOrIdParceiro(Long idCategoria, Long idParceiro);

    // Corrigido para tipos mais específicos
    List<Desafio> findSuggestedChallenges(String preferencias, String metas);

    List<Desafio> findChallengesByClientId(Long cpf);
}
