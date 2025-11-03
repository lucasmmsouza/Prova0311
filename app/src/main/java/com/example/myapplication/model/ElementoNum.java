package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ElementoNum {
    @SerializedName("valor")
    private int valor;

    public int getValor() { return valor; }
}