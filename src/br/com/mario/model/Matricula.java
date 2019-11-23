/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.sql.Date;

/**
 *
 * @author mario
 */
public class Matricula {

    private int id;
    private Aluno aluno;
    private Turma turma;
    private Date dataMatricula;

    public Matricula() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", aluno=" + aluno + ", turma=" + turma + ", dataMatricula=" + dataMatricula + '}';
    }

}
