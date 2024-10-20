package com.habituau.HabitUAU_WEB.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.habituau.HabitUAU_WEB.model.entity.*;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    //Buscar usuário por e-mail (para login e verificação de existência)
    Optional<Cliente> findByEmail(String email);
    
    boolean existsByEmail(String email);

    Optional<Cliente> findByEmailAndSenha(String email, String senha);

	List<DesafioInscrito> findActiveChallengesByCPF(String cpf);
	
	Optional<Cliente> findByCPF(String cpf);
}
