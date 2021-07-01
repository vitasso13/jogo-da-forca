package com.example.jogodaforca;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jogodaforca.databinding.FragmentAdicionarPalavraBinding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class adicionarPalavra extends Fragment {
    private static final String ARQUIVO = null;
    private FragmentAdicionarPalavraBinding binding;
    private static Context context;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAdicionarPalavraBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.voltarInicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(adicionarPalavra.this)
                        .navigate(R.id.action_adicionarPalavra_to_FirstFragment);
            }
        });
        binding.aplicarAdicionarPalavra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  escrever();
            }
        });
    }


    public void escrever(){
        String texto =  ((MainActivity)getActivity()).ler();
        ((MainActivity)getActivity()).escrever(binding.palavraNova.getText().toString(), texto);
    }

    public void ler(View v){
        try{
            BufferedReader br = new BufferedReader(new FileReader(getArmazenamento(true)));
            String texto;
            texto = br.readLine();
//            tvResultado.setText(texto);
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }




    public File getArmazenamento (boolean cache){
        if (cache){
            return new File(context.getCacheDir(), ARQUIVO);
        }else{
            return new File(context.getFilesDir(),ARQUIVO);
        }

    }




}