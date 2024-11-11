package com.habituau.HabitUAU_WEB.api.dto;

import java.util.Date;

public class AmizadeDTO {

    private String cpfCliente1;
    private String nomeCliente1;
    private String cpfCliente2;
    private String nomeCliente2;
    private Date dataAmizade;

    // Construtor completo
    public AmizadeDTO(String cpfCliente1, String nomeCliente1, String cpfCliente2, String nomeCliente2, Date dataAmizade) {
        this.cpfCliente1 = cpfCliente1;
        this.nomeCliente1 = nomeCliente1;
        this.cpfCliente2 = cpfCliente2;
        this.nomeCliente2 = nomeCliente2;
        this.dataAmizade = dataAmizade;
    }

    // Getters e Setters
    public String getCpfCliente1() {
        return cpfCliente1;
    }

    public void setCpfCliente1(String cpfCliente1) {
        this.cpfCliente1 = cpfCliente1;
    }

    public String getNomeCliente1() {
        return nomeCliente1;
    }

    public void setNomeCliente1(String nomeCliente1) {
        this.nomeCliente1 = nomeCliente1;
    }

    public String getCpfCliente2() {
        return cpfCliente2;
    }

    public void setCpfCliente2(String cpfCliente2) {
        this.cpfCliente2 = cpfCliente2;
    }

    public String getNomeCliente2() {
        return nomeCliente2;
    }

    public void setNomeCliente2(String nomeCliente2) {
        this.nomeCliente2 = nomeCliente2;
    }

    public Date getDataAmizade() {
        return dataAmizade;
    }

    public void setDataAmizade(Date dataAmizade) {
        this.dataAmizade = dataAmizade;
    }
}
