/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mario.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class Instrutor {

    private int id;
    private String rg;
    private String nome;
    private Date nascimento;
    private String titulacao;
    private List<Telefone> telefones;
    private List<Turma> turmas;

    public Instrutor() {
        this.telefones = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public Instrutor(int id, String rg, String nome, Date nascimento, String titulacao, List<Telefone> telefones, List<Turma> turmas) {
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.nascimento = nascimento;
        this.titulacao = titulacao;
        this.telefones = telefones;
        this.turmas = turmas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
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
