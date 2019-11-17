/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.util.Date;

/**
 *
 * @author mario
 */
public class Chamada {

    private int id;
    private Date data;
    private boolean presente;
    private Aluno aluno;
    private Turma turma;

    public Chamada() {
    }

    public Chamada(int id, Date data, boolean presente, Aluno aluno, Turma turma) {
        this.id = id;
        this.data = data;
        this.presente = presente;
        this.aluno = aluno;
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Chamada{" + "id=" + id + ", data=" + data + ", presente=" + presente + ", aluno=" + aluno + ", turma=" + turma + '}';
    }

}
