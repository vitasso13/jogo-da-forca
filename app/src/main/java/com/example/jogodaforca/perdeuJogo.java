package com.example.jogodaforca;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jogodaforca.databinding.FragmentPerdeuJogoBinding;
import com.example.jogodaforca.databinding.FragmentSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link perdeuJogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perdeuJogo extends Fragment {
    private FragmentPerdeuJogoBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPerdeuJogoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(perdeuJogo.this)
                        .navigate(R.id.action_perdeuJogo_to_FirstFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}