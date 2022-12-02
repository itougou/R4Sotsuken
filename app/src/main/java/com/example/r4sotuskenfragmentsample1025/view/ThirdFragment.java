package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.adapter.ItemAdapter;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentThirdBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

import java.util.List;

/**
 * 選手名一覧表示フラグメント
 */
public class ThirdFragment extends Fragment implements ItemAdapter.PlayerInterface{

    private NavController navController;
    private BaseballViewModel mBaseballViewModel;
    private FragmentThirdBinding fragmentThirdBinding;
    private ItemAdapter adapter;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //2022.10.19 ito
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        adapter = new ItemAdapter(this);
        fragmentThirdBinding.rvPlayer.setAdapter(adapter);

        //2022.10.24 ito
        // Get a new or existing ViewModel from the ViewModelProvider.
        mBaseballViewModel = new ViewModelProvider(requireActivity()).get(BaseballViewModel.class);

        //ビュー・モデルのgetAllメソッド呼び出しと、
        // getAllで取り出す値（List<PlayerAndTeam>）が変化したときのコールバック処理（onChanged）登録
        mBaseballViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<PlayerAndTeam>>() {
            @Override
            public void onChanged(List<PlayerAndTeam> playerAndTeam) {
                //変化後の結果をItemAdapterに渡し選手一覧画面へ反映してもらう
                adapter.submitList(playerAndTeam);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentThirdBinding = FragmentThirdBinding.inflate(inflater,container,false);
        return fragmentThirdBinding.getRoot();
    }

    //選手名のクリック時の処理
    @Override
    public void onItemClick(PlayerAndTeam playerAndTeam) {
        mBaseballViewModel.setPlayerAndTeam(playerAndTeam);

        //2022.11.29 ito　ここではDaoの検索結果をうまく取り出せない？nullになるので
        // FourceFragmentの onViewCreatedで 検索するようにする
        //List<HandlePositionAndPosition> l = mBaseballViewModel.getHandlePositionAndPosition(playerAndTeam.getId()).getValue();
        //Log.i("★ThirdFragment","getHandlePositionAndPosition:"+l);

        navController.navigate(R.id.action_thirdFragment_to_fourthFragment);    //FourthFragmentへ画面遷移
        Log.i("★ThirdFragment","onItemClick()");
    }
}