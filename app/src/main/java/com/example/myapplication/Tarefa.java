package com.example.myapplication;

import java.io.Serializable;

public class Tarefa implements Serializable {

    // Atributos
    private String id;
    private String nome;
    private String descricao;
    private String prioridade;
    private String categoria;
    private boolean concluida ;

    public Tarefa(String id, String nome, String descricao, String prioridade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.concluida = false;
    }



    // Métodos Getter e Setter para concluida
    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }


    // Métodos Getter e Setter para id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Métodos Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos Getter e Setter para descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Métodos Getter e Setter para prioridade
    public String getPrioridade() {
        return prioridade;
    }


    // Métodos Getter e Setter para categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"," +
                "\"nome\": \"" + nome + "\"," +
                "\"descricao\": \"" + descricao + "\"," +
                "\"prioridade\": " + prioridade + "," +
                "\"categoria\": \"" + categoria + "\"" +
                "\"concluida\": \"" + concluida + "\"" +
                "}";
    }
}