package com.habituau.HabitUAU_WEB.exceptions;

//aqui são geradas as exceptions de regras de negócio
//esse tipo de implementação veio do curso da udemy!
//é uma classe "padrão"p/ lidar com as exceptions

public class RegraNegocioException extends RuntimeException {
	public RegraNegocioException(String msg) {
		super(msg);
	}
}
