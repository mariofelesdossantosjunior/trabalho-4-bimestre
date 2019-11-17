/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author mario
 */
public class Turma {

    private int id;
    private String horario;
    private Float duracao;
    private Date dataInicio;
    private Date dataFim;
    private Instrutor instrutor;
    private Atividade atividade;
    private List<Aluno> alunos;

    public Turma() {
    }

    public Turma(int id, String horario, Float duracao, Date dataInicio, Date dataFim, Instrutor instrutor, Atividade atividade, List<Aluno> alunos) {
        this.id = id;
        this.horario = horario;
        this.duracao = duracao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.instrutor = instrutor;
        this.atividade = atividade;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Turma{" + "id=" + id + ", horario=" + horario + ", duracao=" + duracao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", instrutor=" + instrutor + ", atividade=" + atividade + '}';
    }

}
