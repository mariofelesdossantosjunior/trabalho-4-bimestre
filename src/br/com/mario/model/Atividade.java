/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.util.List;

/**
 *
 * @author mario
 */
public class Atividade {

    private int id;
    private String nome;
    List<Turma> turmas;

    public Atividade() {
    }

    public Atividade(int id, String nome, List<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.turmas = turmas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return nome;
    }

}
