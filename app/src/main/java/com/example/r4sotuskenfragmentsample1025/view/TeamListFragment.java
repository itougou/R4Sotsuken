package com.example.r4sotuskenfragmentsample1025.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.adapter.TeamAdapter;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentTeamListBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

import java.util.List;

/**
 * ﾁｰﾑ名一覧
 */
public class TeamListFragment extends Fragment implements TeamAdapter.TeamInterface{

    private NavController navController;
    private BaseballViewModel mBaseballViewModel;
    private TeamAdapter adapter;
    private FragmentTeamListBinding fragmentTeamListBinding;

    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTeamListBinding = FragmentTeamListBinding.inflate(inflater,container,false);
        return fragmentTeamListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        adapter = new TeamAdapter(this );
        fragmentTeamListBinding.rvTeams.setAdapter(adapter);

        //ビュー・モデル取得
        mBaseballViewModel = new ViewModelProvider(requireActivity()).get(BaseballViewModel.class);

        //ビュー・モデルのgetAllTeams呼び出しと、
        // getAllTeamsで取り出す値（List<Team>）が変化したときのコールバック処理（onChanged）登録
        mBaseballViewModel.getAllTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> team) {
                //変化後の結果をTeamAdapterに渡しチーム一覧画面へ反映してもらう
                Log.d("★TeamListFragment","getAllTeams→onChanged");

                adapter.submitList(team);
            }
        });
        Log.d("★★★★ TeamListFragent"," onViewCreated()");

        //チーム追加ボタンクリックイベントハンドラー登録
        fragmentTeamListBinding.btnAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // チーム追加フォーム画面 addTeamFragment へ遷移させる
                Log.d("★TeamListFragment","btnAddTeam onClick()");
                navController.navigate(R.id.action_teamListFragment_to_addTeamFragment);
            }
        });

    }

    //勝利数 DB更新処理 2022.11.15 ito
    @Override
    public void onUpdateWins( Team team ){

        Log.i("★TeamListFragment","onUpdateWins() Team mae:"+team.getName()+",Team.win:"+team.getWin());
        //mBaseballViewModel.setTeam(team);
        mBaseballViewModel.updateTeamWin(team);

        //Toast.makeText(getActivity(), "チーム情報DBを更新しました", Toast.LENGTH_SHORT).show();

        Log.i("★TeamListFragment","onUpdateWins() Team ato:"+team.getName()+",Team.win:"+team.getWin());

    }

    //チーム名クリック時の処理
    @Override
    public void onItemClick(Team team) {
        Log.i("★TeamListFragment","onItemClick() Team:"+team.getName());
        mBaseballViewModel.setTeam(team);
        navController.navigate(R.id.action_teamListFragment_to_teamEditFragment);
    }

}