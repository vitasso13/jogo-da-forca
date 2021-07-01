package com.example.jogodaforca;

import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jogodaforca.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jogodaforca.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String ARQUIVO = "memoria_interna.txt";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final String palavrasPadrao[]= {"pressa", "maroto","solene","herege","embora","buscar","quanto","danado","acesso","axioma"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!verificaArquivo()){
            iniciaArquivoPadrao();
        }



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void criarAlert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Você Errou");
        dialog.setPositiveButton("tente novamente", null);
        dialog.show();
    }


    public void escrever(String[] palavraNova){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(getArmazenamento(true)));

            int tamanho = palavraNova.length;
            for(int i=0; i<tamanho ;i++){
                bw.write(palavraNova[i]+"/");
            }


            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String ler(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(getArmazenamento(true)));
            String texto;
            texto = br.readLine();
            br.close();
            return texto;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }




    public File getArmazenamento (boolean cache) {
        if (cache) {
            return new File(getCacheDir(), ARQUIVO);
        } else {
            return new File(getFilesDir(), ARQUIVO);
        }

    }
    public boolean verificaArquivo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(getArmazenamento(true)));
            //verificar conteudo
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void iniciaArquivoPadrao(){
        escrever(palavrasPadrao);
    }

}