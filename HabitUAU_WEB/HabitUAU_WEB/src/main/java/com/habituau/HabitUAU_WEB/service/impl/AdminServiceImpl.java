package com.habituau.HabitUAU_WEB.service.impl;

import com.habituau.HabitUAU_WEB.exceptions.ErroAutenticacao;
import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Admin;
import com.habituau.HabitUAU_WEB.model.repository.AdminRepository;
import com.habituau.HabitUAU_WEB.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin registerAdmin(Long RE, String email, String Nome, String Sobrenome, Long filialId, String senha, String telefone) {
        if (adminRepository.existsByEmail(email)) {
            throw new RegraNegocioException("Já existe um admin cadastrado com este e-mail.");
        }

        Admin admin = new Admin(RE, email, Nome, Sobrenome, null, senha, telefone);
        return adminRepository.save(admin);
    }

    @Override
    public Admin loginAdmin(String email, String senha) {
        return adminRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new ErroAutenticacao("Credenciais inválidas."));
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public void updateAdminProfile(Long RE, String Nome, String Sobrenome, String telefone, String senha) {
        Admin admin = adminRepository.findByRE(RE)
                .orElseThrow(() -> new RegraNegocioException("Admin com RE " + RE + " não encontrado."));

        if (Nome != null && !Nome.isEmpty()) admin.setNome(Nome);
        if (Sobrenome != null && !Sobrenome.isEmpty()) admin.setSobrenome(Sobrenome);
        if (telefone != null && !telefone.isEmpty()) admin.setTelefone(telefone);
        if (senha != null && !senha.isEmpty()) admin.setSenha(senha);

        adminRepository.save(admin);
    }

    @Override
    public void deleteAdminByRE(Long RE) {
        if (!adminRepository.existsByRE(RE)) {
            throw new RegraNegocioException("Admin com RE " + RE + " não encontrado.");
        }
        adminRepository.deleteByRE(RE);
    }

    @Override
    public Optional<Admin> findByRE(Long RE) {
        return adminRepository.findByRE(RE);
    }
}
