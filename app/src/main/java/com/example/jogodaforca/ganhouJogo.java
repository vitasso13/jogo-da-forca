package com.example.jogodaforca;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jogodaforca.databinding.FragmentGanhouJogoBinding;
import com.example.jogodaforca.databinding.FragmentPerdeuJogoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ganhouJogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ganhouJogo extends Fragment {
    private FragmentGanhouJogoBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentGanhouJogoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ganhouJogo.this)
                        .navigate(R.id.action_ganhouJogo_to_FirstFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}