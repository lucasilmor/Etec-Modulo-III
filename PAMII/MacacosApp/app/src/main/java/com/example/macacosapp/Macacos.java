package com.example.macacosapp;

public class Macacos {

    //variaveis que representam os campos do banco de dados
    private int id, idade;
    private String nome, especie, dataEntrada;

    //Construtor da classe

    public Macacos(int id, String nome, String especie, String dataEntrada, int idade) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.dataEntrada = dataEntrada;
        this.idade = idade;
    }


    //criando os métodos de acesso getter e setter


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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

