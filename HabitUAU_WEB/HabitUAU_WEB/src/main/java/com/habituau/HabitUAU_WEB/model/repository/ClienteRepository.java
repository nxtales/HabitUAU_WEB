package com.habituau.HabitUAU_WEB.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.habituau.HabitUAU_WEB.model.entity.*;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    //Buscar usuário por e-mail (para login e verificação de existência)
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByEmailAndPassword(String email, String password);

	List<DesafioInscrito> findActiveChallengesByClientId(Long cpf);
	
	Optional<Cliente> findByCpf(String cpf);
}
