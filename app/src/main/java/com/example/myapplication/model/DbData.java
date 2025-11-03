package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DbData {
    @SerializedName("elementos")
    private List<ElementoNome> elementos;

    @SerializedName("simbolos")
    private List<ElementoSimb> simbolos;

    @SerializedName("numeros")
    private List<ElementoNum> numeros;

    public List<ElementoNome> getElementos() { return elementos; }
    public List<ElementoSimb> getSimbolos() { return simbolos; }
    public List<ElementoNum> getNumeros() { return numeros; }
}