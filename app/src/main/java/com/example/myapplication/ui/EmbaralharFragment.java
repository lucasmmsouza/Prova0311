package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer; // Importar o Observer
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.model.Elemento;
import com.example.myapplication.viewmodel.DataViewModel;
import java.util.ArrayList;
import java.util.List;

public class EmbaralharFragment extends Fragment {
    private DataViewModel dataViewModel;
    private ListView listViewEmbaralhar;
    private ArrayAdapter<String> adapter;

    public EmbaralharFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_embaralhar, container, false);
        listViewEmbaralhar = view.findViewById(R.id.listViewEmbaralhar);
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        dataViewModel.getElementos().observe(getViewLifecycleOwner(), new Observer<List<Elemento>>() {
            @Override
            public void onChanged(List<Elemento> elementos) {
                if (elementos != null) {
                    calcularEExibir(elementos);
                }
            }
        });

        return view;
    }

    private void calcularEExibir(List<Elemento> elementos) {
        List<String> resultados = new ArrayList<>();

        for (Elemento el : elementos) {
            int contagemLetras = el.getNome().length();
            int numeroAtomico = el.getNumeroAtomico();
            int resultado = contagemLetras + numeroAtomico;

            String linha = el.getNome() + " (" + contagemLetras + " letras) + " +
                    numeroAtomico + " = " + resultado;
            resultados.add(linha);
        }

        adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1,
                resultados);
        listViewEmbaralhar.setAdapter(adapter);
    }
}