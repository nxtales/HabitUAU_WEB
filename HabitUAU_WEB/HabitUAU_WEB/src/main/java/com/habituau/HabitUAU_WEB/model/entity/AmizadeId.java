package com.habituau.HabitUAU_WEB.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class AmizadeId implements Serializable {

    private String cliente1; 
    private String cliente2; 

    
    public AmizadeId() {}

    public AmizadeId(String cliente1, String cliente2) {
        this.cliente1 = cliente1;
        this.cliente2 = cliente2;
    }

    // Getters e Setters
    public String getCliente1() {
        return cliente1;
    }

    public void setCliente1(String cliente1) {
        this.cliente1 = cliente1;
    }

    public String getCliente2() {
        return cliente2;
    }

    public void setCliente2(String cliente2) {
        this.cliente2 = cliente2;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmizadeId that = (AmizadeId) o;
        return Objects.equals(cliente1, that.cliente1) && 
               Objects.equals(cliente2, that.cliente2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente1, cliente2);
    }
}
