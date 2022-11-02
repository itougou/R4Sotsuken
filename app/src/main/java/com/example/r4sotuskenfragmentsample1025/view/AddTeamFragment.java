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
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentTeamListBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

/**
 * チーム追加画面Fragment
 */
public class AddTeamFragment extends Fragment {

    private FragmentAddTeamBinding fragmentAddTeamBinding;

    private BaseballViewModel mBaseballViewModel;

    private NavController navController;

    public AddTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBaseballViewModel = new ViewModelProvider(this).get(BaseballViewModel.class);
        fragmentAddTeamBinding = FragmentAddTeamBinding.inflate(inflater,container,false);

        View t_at_view = fragmentAddTeamBinding.getRoot();

        //チーム追加ボダンクリックハンドラー登録
        fragmentAddTeamBinding.btnExecAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //画面入力したID,チーム名を元にTeamｲﾝｽﾀﾝｽ生成
                Team team = new Team(fragmentAddTeamBinding.edtTeamId.getText().toString(),fragmentAddTeamBinding.edtTeamName.getText().toString());
                //ビュー・モデルのsetTeamメソッド呼び出し
                mBaseballViewModel.setTeam(team);

                Log.d("★AddTeamFragment","btnExecAdd onClick()");
                //チーム一覧画面Fragmentへ遷移
                navController.navigate(R.id.action_addTeamFragment_to_teamListFragment);
            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_team, container, false);
        return t_at_view;
    }
}