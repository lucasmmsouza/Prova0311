package com.example.myapplication.model;
import java.io.Serializable;

public class Elemento {
    private String nome;
    private String simbolo;
    private int numeroAtomico;

    public Elemento(String nome, String simbolo, int numeroAtomico) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.numeroAtomico = numeroAtomico;
    }

    public String getNome() {
        return nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getNumeroAtomico() {
        return numeroAtomico;
    }

    @Override
    public String toString() {
        return nome + " (" + simbolo + ") - N°: " + numeroAtomico;
    }
}