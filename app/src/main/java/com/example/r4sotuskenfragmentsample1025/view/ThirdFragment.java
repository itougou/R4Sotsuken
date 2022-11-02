package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.adapter.ItemAdapter;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentSecondBinding;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentThirdBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

import java.util.List;

/**
 * 選手名一覧表示フラグメント
 */
public class ThirdFragment extends Fragment implements ItemAdapter.PlayerInterface{

    //2022.10.29 ito
    private NavController navController;

    //2022.10.24 ito
    private BaseballViewModel mBaseballViewModel;

    //2022.10.27 ito
    private FragmentThirdBinding fragmentThirdBinding;


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //2022.10.24 ito
        // Get a new or existing ViewModel from the ViewModelProvider.
        mBaseballViewModel = new ViewModelProvider(this).get(BaseballViewModel.class);

        fragmentThirdBinding = FragmentThirdBinding.inflate(inflater,container,false);
        View t_f_view = fragmentThirdBinding.getRoot();

        //RecyclerViewの取得
        RecyclerView recyclerView = fragmentThirdBinding.rvPlayer;

        //LayoutManagerの設定
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager( mLayoutManager );

        //2022.10.28
        // String dat[]=new String[100];
        // for(int i=0 ; i<100 ;i++){ dat[i]="選手No."+(i+1); }
        //ItemAdapter adapter = new ItemAdapter(dat);
        //recyclerView.setAdapter(adapter);

        //2022.10.29 ito
        // ItemAdapter adapter = new ItemAdapter (new ItemAdapter.ItemDiff());
        ItemAdapter adapter = new ItemAdapter (this);
        fragmentThirdBinding.rvPlayer.setAdapter(adapter);

        //ビュー・モデル取得
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

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false);
        return t_f_view;
    }
    @Override
    public void onItemClick(PlayerAndTeam playerAndTeam) {
        mBaseballViewModel.setPlayerAndTeam(playerAndTeam);
        navController.navigate(R.id.action_thirdFragment_to_fourthFragment);
        Log.i("★ThirdFragment","onItemClick()");
    }
}