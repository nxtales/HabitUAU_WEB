package com.habituau.HabitUAU_WEB.model.entity;

import java.util.List;

public class ProgressReport {
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<DesafioInscritoTarefaCompleta> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<DesafioInscritoTarefaCompleta> tarefas) {
		this.tarefas = tarefas;
	}
	
	private Cliente cliente;
	private List<DesafioInscritoTarefaCompleta> tarefas;
	private List<DesafioInscrito> desafios;
	
	public List<DesafioInscrito> getDesafios() {
		return desafios;
	}
	public void setDesafios(List<DesafioInscrito> desafios) {
		this.desafios = desafios;
	}
	
}
