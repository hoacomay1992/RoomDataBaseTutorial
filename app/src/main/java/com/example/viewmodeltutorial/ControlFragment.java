package com.example.viewmodeltutorial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewmodeltutorial.databinding.FragmentControlBinding;
import com.example.viewmodeltutorial.view_model.MedalViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment implements View.OnClickListener {
    private FragmentControlBinding binding;
    private MedalViewModel medalViewModel;

    public ControlFragment() {

    }

    public static ControlFragment newInstance(String param1, String param2) {
        ControlFragment fragment = new ControlFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentControlBinding.inflate(inflater, container, false);
        medalViewModel = new ViewModelProvider(requireActivity()).get(MedalViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnGoldMinus.setOnClickListener(this);
        binding.btnGoldPlus.setOnClickListener(this);
        binding.btnBronzeMinus.setOnClickListener(this);
        binding.btnBronzePlus.setOnClickListener(this);
        binding.btnSilverMinus.setOnClickListener(this);
        binding.btnSilverPlus.setOnClickListener(this);
        displayMedal();
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.btnGoldMinus) {
            medalViewModel.setNumberOfGoldMedalValue(medalViewModel.getNumberOfGoldMedalValue() - 1);
            displayMedal();
            return;
        }
        if (id == R.id.btnGoldPlus) {
            medalViewModel.setNumberOfGoldMedalValue(medalViewModel.getNumberOfGoldMedalValue() + 1);
            displayMedal();
            return;
        }
        if (id == R.id.btnBronzeMinus) {
            medalViewModel.setNumberOfBronzeMedalValue(medalViewModel.getNumberOfBronzeMedalValue() - 1);
            displayMedal();
            return;

        }
        if (id == R.id.btnBronzePlus) {
            medalViewModel.setNumberOfBronzeMedalValue(medalViewModel.getNumberOfBronzeMedalValue() + 1);
            displayMedal();
            return;
        }

        if (id == R.id.btnSilverMinus) {
            medalViewModel.setNumberOfSilverMedalValue(medalViewModel.getNumberOfSilverMedalValue() - 1);
            displayMedal();
            return;
        }
        if (id == R.id.btnSilverPlus) {
            medalViewModel.setNumberOfSilverMedalValue(medalViewModel.getNumberOfSilverMedalValue() + 1);
            displayMedal();
        }

    }

    private void displayMedal() {
        binding.tvMainGoldNumber.setText("" + medalViewModel.getNumberOfGoldMedalValue());
        binding.tvMainSilverNumber.setText("" + medalViewModel.getNumberOfSilverMedalValue());
        binding.tvMainBronzeNumber.setText("" + medalViewModel.getNumberOfBronzeMedalValue());
    }
}