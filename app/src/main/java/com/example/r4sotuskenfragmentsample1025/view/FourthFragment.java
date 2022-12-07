package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.adapter.PositionAdapter;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentFourthBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerPositionAndPosition;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerWithPosition;
import com.example.r4sotuskenfragmentsample1025.entity.Position;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

import java.util.List;

/**
 * 選手詳細情報表示フラグメント
 */
public class FourthFragment extends Fragment  implements PositionAdapter.PositionInterface{

    FragmentFourthBinding fragmentFourthBinding;
    BaseballViewModel baseballViewModel;
    //2022.11.20 ito
    private PositionAdapter adapter;

    public FourthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        adapter = new PositionAdapter(this);
        fragmentFourthBinding.rvPosition.setAdapter(adapter);

        //2022.11.30 ito　選手・ポジションの情報を取り出しセットする処理
        //ビュー・モデルのgetHandlePositionAndPosition( player_id )メソッド呼び出しと、
        // 取り出す値（List<HandlePositionAndPosition>）が変化したときのコールバック処理（onChanged）登録
        String player_id = baseballViewModel.getPlayerAndTeam().getValue().player.getPlayer_id(); //クリックした行の選手IDを取り出す
        Log.i("★FourceFragment","player.getId()："+player_id);

        /**　2022.12.7 下記の PlayerWithPosition を使って ポジション情報を表示する処理に変更
        baseballViewModel.getPlayerPositionAndPosition( player_id ).observe(getViewLifecycleOwner(), new Observer<List<PlayerPositionAndPosition>>() {
            @Override
            public void onChanged(List<PlayerPositionAndPosition> playerPositionAndPosition) {
                Log.i("★FourceFragment","List<PlayerPositionAndPosition>："+playerPositionAndPosition);
                //変化後の結果をAdapterに渡し、ポジション表示用のリサイクラーViewへ反映してもらう
                adapter.submitList( playerPositionAndPosition );
            }
        });
        **/
        baseballViewModel.getPlayerWithPosition( player_id ).observe(getViewLifecycleOwner(), new Observer<PlayerWithPosition>() {
            @Override
            public void onChanged(PlayerWithPosition playerWithPosition) {
                Log.i("★FourceFragment","List<PlayerWithPosition>："+playerWithPosition);
                //変化後の結果をAdapterに渡し、ポジション表示用のリサイクラーViewへ反映してもらう
                adapter.submitList( playerWithPosition.positions );
            }
        });

        // FourthFragment画面とBaseballViewModelを結びつけるために必要
        fragmentFourthBinding.setBaseballViewModel( baseballViewModel );

    }

    @Override
    public void onItemClick(Position position) {

    }
}