package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesafioRepository extends JpaRepository<Desafio, Long> {

    // Buscar desafios por categoria ou parceiro
    List<Desafio> findByCategoriaOrParceiro(CategoriaDesafio categoria, Parceiro parceiro);
    
    List<Desafio> findByID(Long ID);
    //List<Desafio> findByID(List<Long> desafioIds);

    // Corrigido para tipos mais específicos
    //List<Desafio> findByClientePreferenciasAndClienteMetas(String preferencias, String metas);
    
    //@Query("SELECT d FROM Desafio d WHERE d.cliente.preferencias = :preferencias AND d.cliente.metas = :metas")
    //List<Desafio> findDesafiosByClientePreferenciasAndMetas(@Param("preferencias") String preferencias, @Param("metas") String metas);
    
    // Método para buscar desafios pelo nome (ou parte do nome)
    @Query("SELECT d FROM Desafio d WHERE LOWER(d.Nome) LIKE LOWER(CONCAT('%', :input, '%'))")
    List<Desafio> findByNomeContaining(@Param("input") String input);

    //List<Desafio> findChallengesBy(Long cpf);
}
