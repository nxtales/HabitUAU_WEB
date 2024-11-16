package com.habituau.HabitUAU_WEB.service;

import com.habituau.HabitUAU_WEB.model.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Admin registerAdmin(Long RE, String email, String Nome, String Sobrenome, Long filialId, String senha, String telefone);
    Admin loginAdmin(String email, String senha);
    Optional<Admin> findByEmail(String email);
    void updateAdminProfile(Long RE, String Nome, String Sobrenome, String telefone, String senha);
    void deleteAdminByRE(Long RE);
    Optional<Admin> findByRE(Long RE);
}
