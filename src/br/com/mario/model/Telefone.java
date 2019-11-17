/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

/**
 *
 * @author mario
 */
public class Telefone {

    private int id;
    private String numero;
    private String tipo;
    private Instrutor instrutor;

    public Telefone() {
    }

    public Telefone(String numero, String tipo, Instrutor instrutor) {
        this.numero = numero;
        this.tipo = tipo;
        this.instrutor = instrutor;
    }

    public Telefone(int id, String numero, String tipo, Instrutor instrutor) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.instrutor = instrutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefone other = (Telefone) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", instrutor=" + instrutor + '}';
    }

}
