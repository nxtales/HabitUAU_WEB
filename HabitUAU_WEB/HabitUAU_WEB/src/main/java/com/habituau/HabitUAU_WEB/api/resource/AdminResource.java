package com.habituau.HabitUAU_WEB.api.resource;

import com.habituau.HabitUAU_WEB.api.dto.AdminDTO;
import com.habituau.HabitUAU_WEB.exceptions.ErroAutenticacao;
import com.habituau.HabitUAU_WEB.exceptions.RegraNegocioException;
import com.habituau.HabitUAU_WEB.model.entity.Admin;
import com.habituau.HabitUAU_WEB.model.repository.AdminRepository;
import com.habituau.HabitUAU_WEB.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {

	@Autowired
	private AdminRepository repository;
	
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdminDTO dto) {
        try {
            Admin admin = adminService.registerAdmin(dto.getRE(), dto.getEmail(), dto.getNome(),
                    dto.getSobrenome(), dto.getFilialId(), dto.getSenha(), dto.getTelefone());
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDTO dto) {
        try {
            Admin admin = adminService.loginAdmin(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(admin);
        } catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{re}")
    public ResponseEntity<?> update(@PathVariable("re") Long RE, @RequestBody AdminDTO dto) {
        try {
            adminService.updateAdminProfile(RE, dto.getNome(), dto.getSobrenome(), dto.getTelefone(), dto.getSenha());
            return ResponseEntity.ok("Admin atualizado com sucesso.");
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{re}")
    public ResponseEntity<?> delete(@PathVariable("re") Long RE) {
        try {
            adminService.deleteAdminByRE(RE);
            return ResponseEntity.noContent().build();
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/retrieve/{re}")
    public ResponseEntity<?> retrieveAdminByRE(@PathVariable("re") Long RE) {
        Optional<Admin> adminOpt = adminService.findByRE(RE);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin com RE " + RE + " não encontrado.");
        }
        return ResponseEntity.ok(adminOpt.get());
    }
    
    @GetMapping("/GetAll")
    public ResponseEntity<?> retrieveAll() {
    	return ResponseEntity.ok(repository.findAll());
    }
    
}
