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
public class ItemChamada {

    private int id;
    private Boolean presente;
    Matricula matricula;
    Chamada chamada;

    public ItemChamada() {
    }

    public ItemChamada(Boolean presente, Matricula matricula, Chamada chamada) {
        this.presente = presente;
        this.matricula = matricula;
        this.chamada = chamada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Chamada getChamada() {
        return chamada;
    }

    public void setChamada(Chamada chamada) {
        this.chamada = chamada;
    }

    @Override
    public String toString() {
        return "ItemChamada{" + "id=" + id + ", presente=" + presente + ", matricula=" + matricula + ", chamada=" + chamada + '}';
    }

}
