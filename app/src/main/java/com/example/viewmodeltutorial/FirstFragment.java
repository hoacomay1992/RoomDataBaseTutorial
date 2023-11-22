package com.example.viewmodeltutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.viewmodeltutorial.databinding.FragmentFirstBinding;
import com.example.viewmodeltutorial.view_model.CounterViewModel;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private CounterViewModel counterViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnUp.setOnClickListener(view1 -> {
            counterViewModel.setCount(counterViewModel.getCount() + 1);
            displayCounter();
        });

        binding.btnDown.setOnClickListener(v -> {
            counterViewModel.setCount(counterViewModel.getCount() - 1);
            displayCounter();
        });
        displayCounter();
    }

    private void displayCounter() {
        binding.tvCount.setText("" + counterViewModel.getCount());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}