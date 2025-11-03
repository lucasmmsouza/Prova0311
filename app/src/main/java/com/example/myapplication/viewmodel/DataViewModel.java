package com.example.myapplication.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.auxilia.Conexao;
import com.example.myapplication.auxilia.Conversao;
import com.example.myapplication.model.Elemento;
import com.example.myapplication.model.DbData; // Importar classe POJO principal

import com.google.gson.Gson; // Importar Gson

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataViewModel extends ViewModel {

    private static final String JSON_URL = "https://my-json-server.typicode.com/lucasmmsouza/Prova/db";
    private final MutableLiveData<List<Elemento>> elementosData = new MutableLiveData<>();
    private final MutableLiveData<String> erro = new MutableLiveData<>();
    private boolean dadosCarregados = false;

    public LiveData<List<Elemento>> getElementos() {
        return elementosData;
    }
    public LiveData<String> getErro() {
        return erro;
    }
    public void carregarDados() {
        if (dadosCarregados) {
            return;
        }

        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(JSON_URL);

                Conversao conversao = new Conversao();
                String jsonString = conversao.converter(inputStream);

                if (jsonString == null) {
                    erro.postValue("Falha ao obter dados da rede.");
                    return;
                }

                try {
                    Gson gson = new Gson();
                    DbData dbData = gson.fromJson(jsonString, DbData.class);

                    List<Elemento> listaCombinada = new ArrayList<>();
                    int tamanho = dbData.getElementos().size();

                    if (tamanho == dbData.getSimbolos().size() && tamanho == dbData.getNumeros().size()) {

                        for (int i = 0; i < tamanho; i++) {
                            String nome = dbData.getElementos().get(i).getNome();
                            String simbolo = dbData.getSimbolos().get(i).getSimbolo();
                            int numero = dbData.getNumeros().get(i).getValor();

                            listaCombinada.add(new Elemento(nome, simbolo, numero));
                        }

                        elementosData.postValue(listaCombinada);
                        dadosCarregados = true;
                    } else {
                        erro.postValue("Erro Inconsistencia dados.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    erro.postValue("Erro Json");
                }
            }
        });
    }
}