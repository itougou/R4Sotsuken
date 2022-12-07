package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerWithPosition;
import com.example.r4sotuskenfragmentsample1025.entity.Position;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentSecondBinding;
import java.util.List;

/**
 *
 *  * メインニューフラグメント
 *
 * Vpackage com.example.r4sotuskenfragmentsample1026　を　viewバインド使った処理（findViewByIdを使わないでViewを操作）に改造したもの
**/
public class SecondFragment extends Fragment {

    private NavController navController;

    //2022.10.24 ito
    private BaseballViewModel mBaseballViewModel;

    //2022.10.27 ito
    private FragmentSecondBinding binding;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //2022.10.24 ito
        // Get a new or existing ViewModel from the ViewModelProvider.
        mBaseballViewModel = new ViewModelProvider(this).get(BaseballViewModel.class);

        //2022.10.27 ito コメントアウト＆追加
        // フラグメントで表示する画面をlayoutファイルからインフレートする
        // View s_f_view = inflater.inflate(R.layout.fragment_second, container, false);
        binding = FragmentSecondBinding.inflate(inflater,container,false);
        View s_f_view = binding.getRoot();

        //  ThirdFragmentへ画面遷移　（選手一覧 THIRD FRAGMENT ボタンクリック時）
        // ボタン要素を取得
        //Button bt1 = s_f_view.findViewById(R.id.btnThird);
        // ボタンクリックリスナー設定
        // bt1.setOnClickListener(new View.OnClickListener() {
        binding.btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ThirdFragmentへ遷移させる
                Log.d("★SecondFragment","onClick() To Third Fragment");
                navController.navigate(R.id.action_secondFragment_to_thirdFragment);
            }
        });

        //チーム名（TEAMLISTFLAGENTへ表示）ボタンクリックイベントハンドラー登録
        binding.buttonTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Team List Fragmentへ遷移させる
                Log.d("★SecondFragment","onClick() To Team List Fragment");
                navController.navigate(R.id.action_secondFragment_to_teamListFragment);
            }
        });

        //  チーム名一覧表示（ おまけ ）ボタンクリック時
        binding.buttonTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //2022.10.25 21:38 ito
                // Create the observer which updates the UI.
                final Observer<List<Team>> searchObserver = new Observer<List<Team>>() {
                    @Override
                    public void onChanged(List<Team> teams) {
                        Log.d("★SecondFragment","onClick()team 　TextView binding.txtTeams="+binding.txtTeams);
                        binding.txtTeams.setText("");
                        for(Team t:teams){
                            binding.txtTeams.setText(binding.txtTeams.getText()+"\n\r"+t.getName());
                        }
                    }
                };
                mBaseballViewModel.getAllTeams( ).observe(getViewLifecycleOwner(), searchObserver );
                //
                Log.d("★SecondFragment","onClick()team end");
            }
        });


        //  選手名／身長／チーム名（おまけ） ボタンクリック時
        //Button bt3 = s_f_view.findViewById(R.id.buttonPlayerAndTeam);
        // ボタンクリックリスナー設定
        //bt3.setOnClickListener(new View.OnClickListener() {
        binding.buttonPlayerAndTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //2022.10.25 21:38 ito
                // Create the observer which updates the UI.
                final Observer<List<PlayerAndTeam>> searchObserver = new Observer<List<PlayerAndTeam>>() {
                    @Override
                    public void onChanged(List<PlayerAndTeam> playerAndTeam ) {
                        //TextView tv = s_f_view.findViewById(R.id.txtTeams);
                        Log.d("★SecondFragment","onClick()buttonPlayerAndTeam　TextView binding.buttonPlayerAndTeam ="+binding.buttonPlayerAndTeam);
                        binding.txtTeams.setText("");
                        for(PlayerAndTeam t:playerAndTeam){
                            Log.d("★SecondFragment","onChanged player:"+t.player);
                            Log.d("★SecondFragment","onChanged team:"+t.team);
                            binding.txtTeams.setText(binding.txtTeams.getText()+"\n\r"+t.player.getName()+"/"+t.player.getHeight()+"\t/\t"+t.team.getName());
                        }
                    }
                };
                mBaseballViewModel.getAll( ).observe(getViewLifecycleOwner(), searchObserver );
                //
                Log.d("★SecondFragment","onClick()buttonPlayerAndTeam end");

            }
        });

        //2022.12.5
        //  選手／ポジション（おまけ） ボタンクリック時
        //Button bt3 = s_f_view.findViewById(R.id.buttonbuttonPlayerAndPosition);
        // ボタンクリックリスナー設定
        //bt3.setOnClickListener(new View.OnClickListener() {
        binding.buttonPlayerAndPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*** 2022.12.6　PlayerPositionAndPosition（1対1）　から　PlayerWithPosition（多対多） を使う処理に変更
                // Create the observer which updates the UI.
                final Observer<List<PlayerPositionAndPosition>> searchObserver = new Observer<List<PlayerPositionAndPosition>>() {
                    @Override
                    public void onChanged(List<PlayerPositionAndPosition> playerPositionAndPosition ) {
                        //TextView tv = s_f_view.findViewById(R.id.txtTeams);
                        Log.d("★SecondFragment","onClick()buttonbuttonPlayerAndPosition　TextView binding.buttonPlayerAndPosition ="+binding.buttonPlayerAndPosition);
                        binding.txtTeams.setText("");
                        for(PlayerPositionAndPosition t:playerPositionAndPosition){
                            Log.d("★SecondFragment","onChanged player:"+t.getPlayer_id());
                            Log.d("★SecondFragment","onChanged team:"+t.getPosition_id());
                            binding.txtTeams.setText(binding.txtTeams.getText()+"\n\r"+t.getPlayer_id()+"/"+t.getPosition_id());
                        }
                    }
                };
                mBaseballViewModel.getPlayerPositonAndPosition().observe(getViewLifecycleOwner(), searchObserver );
 ***/
                Log.d("★SecondFragment","onClick()buttonbuttonPlayerAndPosition end");
                // Create the observer which updates the UI.
                final Observer<List<PlayerWithPosition>> searchObserver2 = new Observer <List<PlayerWithPosition>>() {
                    @Override
                    public void onChanged(List<PlayerWithPosition> playerWithPositions ) {
                        //TextView tv = s_f_view.findViewById(R.id.txtTeams);
                        binding.txtTeams.setText("");
                        for(PlayerWithPosition pwp : playerWithPositions) {
                            Log.d("★SecondFragment", "onChanged PlayerID:" + pwp.player.getPlayer_id());
                            binding.txtTeams.setText(binding.txtTeams.getText() + pwp.player.getPlayer_id() +"選手  　");
                            for (Position p : pwp.positions) {
                                binding.txtTeams.setText(binding.txtTeams.getText() + "\n\r　　　" + p.getPosition_id() + "/" + p.getName());
                            }

                            binding.txtTeams.setText(binding.txtTeams.getText() + "\n\r");
                        }
                    }
                };
                mBaseballViewModel.getPlayerWithPosition().observe(getViewLifecycleOwner(), searchObserver2 );
                //
                Log.d("★SecondFragment","onClick()buttonbuttonPlayerAndPosition end");

            }
        });
        //2022.10.28 ito
        //現在未使用？
        mBaseballViewModel.getAllPlayers( ).observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> playerAndTeams) {
                Log.d("★SecondFragment","onCreateView()-> mBaseballViewModel.getAllPlayers( ).observe");
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false);
        return s_f_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

}