package com.example.myapplication.model;
import com.google.gson.annotations.SerializedName;


public class ElementoNome {
    @SerializedName("elemento")
    private String nome;

    public String getNome() { return nome; }
}