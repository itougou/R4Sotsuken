package com.example.r4sotuskenfragmentsample1025.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentSecondBinding;
import com.example.r4sotuskenfragmentsample1025.databinding.FragmentStartBinding;

/**
 * ようこそ画面フラグメント
 * Vpackage com.example.r4sotuskenfragmentsample1026　を　viewバインド使った処理（findViewByIdを使わないでViewを操作）に改造したもの
 */
public class StartFragment extends Fragment {

    private NavController navController;

    //2022.10.27 ito
    private @NonNull FragmentStartBinding binding;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //2022.10.27 ito ViewBindingを使用するためコメントアウト＆追加
        // フラグメントで表示する画面をlayoutファイルからインフレートする
        //View view = inflater.inflate(R.layout.fragment_start, container, false);

        binding = FragmentStartBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        // ボタン要素を取得
        //Button bt1 = view.findViewById(R.id.btnSecond);
        // ボタンクリックリスナー設定
        // bt1.setOnClickListener(new View.OnClickListener() {

        //「ログイン」ボタンクリックイベントハンドラー登録
        binding.btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SecondFragmentへ遷移させる
                Log.d("★StartFragment","onClick()");
                navController.navigate(R.id.action_startFragment_to_secondFragment);
            }
        });
        // Inflate the layout for this fragment上で取得するためコメントアウト
        //return inflater.inflate(R.layout.fragment_start, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

}