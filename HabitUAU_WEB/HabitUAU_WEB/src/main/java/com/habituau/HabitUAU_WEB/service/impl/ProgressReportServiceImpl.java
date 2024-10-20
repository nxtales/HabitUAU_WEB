package com.habituau.HabitUAU_WEB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habituau.HabitUAU_WEB.model.entity.Cliente;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscrito;
import com.habituau.HabitUAU_WEB.model.entity.DesafioInscritoTarefaCompleta;
import com.habituau.HabitUAU_WEB.model.entity.ProgressReport;
import com.habituau.HabitUAU_WEB.model.repository.ClienteRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosRepository;
import com.habituau.HabitUAU_WEB.model.repository.DesafioInscritosTarefasCompletasRepository;
import com.habituau.HabitUAU_WEB.service.ProgressReportService;

@Service
public class ProgressReportServiceImpl implements ProgressReportService {

	private final ClienteRepository clienteRepository;
	private final DesafioInscritosRepository desafioInscritosRepository;
	private final DesafioInscritosTarefasCompletasRepository desafiotarefascompletasrepository;

	@Autowired
	public ProgressReportServiceImpl(ClienteRepository clienteRepository,
			DesafioInscritosRepository desafioInscritosRepository, DesafioInscritosTarefasCompletasRepository desafiotarefascompletasrepository) {
		this.clienteRepository = clienteRepository;
		this.desafioInscritosRepository = desafioInscritosRepository;
		this.desafiotarefascompletasrepository = desafiotarefascompletasrepository;
	}

	@Override
	public ProgressReport generateWeeklyProgressReport(Long cpf) {
		if (!isValidCPF(cpf)) {
			throw new IllegalArgumentException("CPF inválido");
		}

		Cliente cliente = clienteRepository.findByCPF(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		List<DesafioInscritoTarefaCompleta> tarefascompletas = desafiotarefascompletasrepository
				.findAllByCliente(cliente);

		// Gerar relatório com base nos desafios completados
		ProgressReport report = new ProgressReport();
		report.setCliente(cliente);
		report.setTarefas(tarefascompletas);

		return report;
	}

	@Override
	public ProgressReport generateMonthlyProgressReport(Long cpf) {
		if (!isValidCPF(cpf)) {
			throw new IllegalArgumentException("CPF inválido");
		}

		Cliente cliente = clienteRepository.findById(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		List<DesafioInscritoTarefaCompleta> tarefascompletas = desafiotarefascompletasrepository
				.findAllByCliente(cliente);

		// Gerar relatório com base nos desafios completados
		ProgressReport report = new ProgressReport();
		report.setCliente(cliente);
		report.setTarefas(tarefascompletas);

		return report;
	}

	// Validação de CPF
	private boolean isValidCPF(Long cpf) {
		String cpfString = String.valueOf(cpf);
		return cpfString.matches("\\d{11}");
	}

	@Override
	public ProgressReport getChallengeHistory(Long cpf) {
		if (!isValidCPF(cpf)) {
			throw new IllegalArgumentException("CPF inválido");
		}

		Cliente cliente = clienteRepository.findById(cpf.toString())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		List<DesafioInscrito> desafios = desafioInscritosRepository.findByClienteCPF(cpf.toString());

		// Gerar relatório com base nos desafios completados
		ProgressReport report = new ProgressReport();
		report.setCliente(cliente);
		report.setDesafios(desafios);

		return report;
	}

}
