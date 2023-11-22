package com.example.viewmodeltutorial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewmodeltutorial.databinding.FragmentDetailBinding;
import com.example.viewmodeltutorial.view_model.MedalViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private MedalViewModel medalViewModel;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        medalViewModel = new ViewModelProvider(requireActivity()).get(MedalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medalViewModel.getNumberOfGoldMedal().observe(requireActivity(), integer -> displayMedal());
        medalViewModel.getNumberOfSilverMedal().observe(requireActivity(), integer -> displayMedal());
        medalViewModel.getNumberOfBronzeMedal().observe(requireActivity(), integer -> displayMedal());

    }

    private void displayMedal() {
        binding.tvDetailGoldNumber.setText(medalViewModel.getNumberOfGoldMedalValue() + " Huy Chương");
        binding.tvDetailSilverNumber.setText(medalViewModel.getNumberOfSilverMedalValue() + " Huy Chương");
        binding.tvDetailBronzeNumber.setText(medalViewModel.getNumberOfBronzeMedalValue() + " Huy Chương");
    }
}