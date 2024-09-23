package com.habituau.HabitUAU_WEB.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habituau.HabitUAU_WEB.model.entity.*;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    //Buscar usuário por e-mail (para login e verificação de existência)
    Optional<Cliente> findByEmail(String email);
}
