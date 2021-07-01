package com.example.jogodaforca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.jogodaforca.databinding.FragmentFirstBinding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private final String palavrasPadrao[]= {"pressa", "maroto","solene","herege","embora","buscar","quanto","danado","acesso","axioma"};
    private String palavrasArquivo[] ={};
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_testeDeTela2);
            }
        });
        binding.botaoAdicionarPalavra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_adicionarPalavra);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    public void escrever(View v){
//        try{
//            BufferedWriter bw = new BufferedWriter(new FileWriter(getArmazenamento(true)));
//            bw.write(binding.palav.getText().toString());
//            bw.close();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void ler(View v){
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(getArmazenamento(true)));
//            String texto;
//            texto = br.readLine();
//            tvResultado.setText(texto);
//            br.close();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    public File getArmazenamento (boolean cache){
//        if (cache){
//            return new File(getCacheDir(), ARQUIVO);
//        }else{
//            return new File(getFilesDir(),ARQUIVO);
//        }
//
//    }

}