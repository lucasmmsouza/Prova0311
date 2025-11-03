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
import java.util.List;

public class MostrarFragment extends Fragment {

    private DataViewModel dataViewModel;
    private ListView listViewMostrar;
    private ArrayAdapter<Elemento> adapter;

    public MostrarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar, container, false);
        listViewMostrar = view.findViewById(R.id.listaElementos);
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        dataViewModel.getElementos().observe(getViewLifecycleOwner(), new Observer<List<Elemento>>() {
            @Override
            public void onChanged(List<Elemento> elementos) {
                adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_list_item_1,
                        elementos);
                listViewMostrar.setAdapter(adapter);
            }
        });

        dataViewModel.getErro().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String erro) {
                if (erro != null) {
                    Toast.makeText(requireContext(), erro, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}