package com.habituau.HabitUAU_WEB.service;
import java.util.Date;
import java.util.Optional;

import com.habituau.HabitUAU_WEB.model.entity.*;

public interface UserService {

    Cliente registerUser(String email, String password, String nome, String sobrenome, Date dataNascimento, String genero, String cep, String cidade, String pais, String telefone);

    Cliente loginUser(String email, String password);

    Optional<Cliente> findByEmail(String email);

    void updateUserProfile(Long cpf, String nome, String sobrenome, String genero, String cidade, String pais, String telefone);

    boolean canEnrollInChallenge(String cpf);

	void registerUser(Cliente cliente);

}