package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentAddTeamBinding;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentFourthBinding;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentTeamEditBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

/**
 * チーム情報編集画面フラグメント
 */
public class TeamEditFragment extends Fragment {

    FragmentTeamEditBinding fragmentTeamEditBinding;

    BaseballViewModel mBaseballViewModel;

    private NavController navController;

    public TeamEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        mBaseballViewModel = new ViewModelProvider(requireActivity()).get(BaseballViewModel.class);
        fragmentTeamEditBinding.setBaseballViewModel(mBaseballViewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBaseballViewModel = new ViewModelProvider(this).get(BaseballViewModel.class);
        fragmentTeamEditBinding = FragmentTeamEditBinding.inflate(inflater, container, false);

        View t_at_view = fragmentTeamEditBinding.getRoot();

        //チーム情報更新ボダンクリックハンドラー登録
        fragmentTeamEditBinding.btnExecUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ビュー・モデルのsetTeamメソッド呼び出し
                mBaseballViewModel.updateTeam( fragmentTeamEditBinding.edtTeamName.getText().toString());

                Log.d("★TeamEditFragment","btnExecUpdate onClick()");
                //チーム一覧画面Fragmentへ遷移
                navController.navigate(R.id.action_teamEditFragment_to_teamListFragment);
            }
        });

        return t_at_view;

    }
}