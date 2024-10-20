package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmizadesRepository extends JpaRepository<Amizade, AmizadeId> {

    // Buscar amizades por um dos CPFs de clientes
	 @Query("SELECT a FROM Amizade a WHERE a.cliente1.CPF = :cpf OR a.cliente2.CPF = :cpf")
	 List<Amizade> findByClienteCpf(String cpf);

}