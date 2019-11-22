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
public class Aluno {

    private int id;
    private Date dataMatricula;
    private String nome;
    private String endereco;
    private String telefone;
    private Date nascimento;
    private Float altura;
    private Float peso;
    private Turma turma;

    public Aluno() {
    }

    public Aluno(int id, Date dataMatricula, String nome, String endereco, String telefone, Date nascimento, Float altura, Float peso, Turma turma) {
        this.id = id;
        this.dataMatricula = dataMatricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.altura = altura;
        this.peso = peso;
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", dataMatricula=" + dataMatricula + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", nascimento=" + nascimento + ", altura=" + altura + ", peso=" + peso + ", turma=" + turma + '}';
    }

}
