package com.example.myapplication.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.MainActivity;
import com.example.myapplication.auxilia.Conexao;
import com.example.myapplication.model.Elemento;
import com.example.myapplication.ui.EmbaralharFragment;

import org.json.JSONArray;
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
        executor.execute(() -> {

                Conexao conexao = new Conexao();
            InputStream inputStream = conexao.obterRespostaHTTP(JSON_URL);

            JSONObject jsonObject = null;
            try {
            /// aaaaaaaaaaaaaaaaaa

                if (tamanho == jsonSimbolos.length() && tamanho == jsonNumeros.length()) {
                    for (int i = 0; i < tamanho; i++) {
                        String nome = jsonElementos.getString(i);
                        String simbolo = jsonSimbolos.getString(i);
                        int numero = jsonNumeros.getInt(i);
                        listaCombinada.add(new Elemento(nome, simbolo, numero));
                    }
                    elementosData.postValue(listaCombinada);
                    dadosCarregados = true;
                } else {

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        });
    }
}
