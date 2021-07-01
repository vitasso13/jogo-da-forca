package com.example.jogodaforca;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.jogodaforca.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private final String palavras[] = {"pressa", "maroto","solene","herege","embora","buscar","quanto","danado","acesso","axioma"};
    private boolean done[]={false, false, false, false, false, false};
    private String palavraSorteada = "";
    private String letrasErradas = "";
    private int tempoJogado=180;
    private int erros = 0;
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            tempoJogado-=1;
            if(tempoJogado >=0 && erros<5){
                binding.tempoDeJogo.setText("Tempo Restante: " + tempoJogado + " segundos");
                timer();
            }else {
                finalizaJogoPerdeu();
            }
        }
    };
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sorteiaPalavra();
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        binding.botaoTentativaLetra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = binding.letraTentativa.getText().toString();
                char letra = texto.charAt(0);
                pegaAsLetras(letra);
            }
        });
        timer();
    }

    public void pegaAsLetras(char letra) {
        char vetorDaPalavra[] = palavraSorteada.toCharArray();
        int tamanho = vetorDaPalavra.length;
        int achou = -1;
        for (int i = 0; i < tamanho; i++) {
            if (vetorDaPalavra[i] == Character.toLowerCase(letra)) {
                achou = i;
                //testar
                switch(achou){
                    case 0:
                        binding.letra0.setText(Character.toString(vetorDaPalavra[0]));
                        done[0]=true;
                        break;
                    case 1:
                        binding.letra1.setText(Character.toString(vetorDaPalavra[1]));
                        done[1]=true;
                        break;
                    case 2:
                        binding.letra2.setText(Character.toString(vetorDaPalavra[2]));
                        done[2]=true;
                        break;
                    case 3:
                        binding.letra3.setText(Character.toString(vetorDaPalavra[3]));
                        done[3]=true;
                        break;
                    case 4:
                        binding.letra4.setText(Character.toString(vetorDaPalavra[4]));
                        done[4]=true;
                        break;
                    case 5:
                        binding.letra5.setText(Character.toString(vetorDaPalavra[5]));
                        done[5] = true;
                        break;
                    default:
                }
                if(done[0] == true && done[1] == true && done[2] == true && done[3] == true && done[4] == true && done[5] == true ){
                    finalizaJogoGanhou();
                }


            }
        }
        if(achou == -1){
            erros += 1;
            enforcarBoneco(erros);
            letrasErradas += Character.toLowerCase(letra);
            binding.historicoLetras.setText(letrasErradas);
            if(erros!=5){
                ((MainActivity)getActivity()).criarAlert();
            }
        }
    }


    public void sorteiaPalavra() {
        int sorteado = -1;
        int tamanho = palavras.length;
        sorteado = (int) (1 + (Math.random() * tamanho));
        palavraSorteada = palavras[sorteado];
    }
    public void enforcarBoneco(int erros){
        switch(erros){
            case 1:
                binding.tronco.setVisibility(View.INVISIBLE);
                break;
            case 2:
                binding.bracoEsquerdo.setVisibility(View.INVISIBLE);
                break;
            case 3:
                binding.bracoDireito.setVisibility(View.INVISIBLE);
                break;
            case 4:
                binding.pernaEsquerda.setVisibility(View.INVISIBLE);
                break;
            case 5:
                binding.pernaDireita.setVisibility(View.INVISIBLE);
                //finalizar jogo
                finalizaJogoPerdeu();
                break;
            default:

        }
    }
    
    private void finalizaJogoPerdeu(){
        handler.removeCallbacks(r);
       NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_perdeuJogo);
    }
    private void finalizaJogoGanhou(){
        handler.removeCallbacks(r);
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_ganhouJogo);
    }








    public void timer()
    {
        handler.postDelayed(r, 1000);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}