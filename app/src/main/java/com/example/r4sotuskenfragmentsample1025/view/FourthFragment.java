package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentFourthBinding;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

/**
 * 選手詳細情報表示フラグメント
 */
public class FourthFragment extends Fragment {

    FragmentFourthBinding fragmentFourthBinding;
    BaseballViewModel baseballViewModel;

    public FourthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //2022.10.29 ito
        fragmentFourthBinding = FragmentFourthBinding.inflate(inflater, container, false);
        return fragmentFourthBinding.getRoot();
        //return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    //2022.10.29 ito
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        baseballViewModel = new ViewModelProvider(requireActivity()).get(BaseballViewModel.class);
        fragmentFourthBinding.setBaseballViewModel(baseballViewModel);
    }
}