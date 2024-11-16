package com.habituau.HabitUAU_WEB.model.repository;

import com.habituau.HabitUAU_WEB.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    // Buscar administrador por e-mail
    Optional<Admin> findByEmail(String email);

	Optional<Admin> findByEmailAndSenha(String email, String senha);

	boolean existsByEmail(String email);
	
	Optional<Admin> findByRE(Long RE);

	boolean existsByRE(Long RE);

	void deleteByRE(Long rE);
}
