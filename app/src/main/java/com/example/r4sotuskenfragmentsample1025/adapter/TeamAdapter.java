package com.example.r4sotuskenfragmentsample1025.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.databinding.TeamIemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Team;;

public class TeamAdapter extends ListAdapter<Team,TeamAdapter.ViewHolder> {

    //2022.10.29 ito  コンストラクタ追加 for Click ハンドラー
    TeamInterface teamInterface;
    public TeamAdapter(TeamInterface teamInterface) {
        super(Team.itemCallback);
        this.teamInterface = teamInterface;
    }

    // セルのレイアウトを読み込んでViewHolderと紐付ける (1セルごとに毎回呼び出される）
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from( viewGroup.getContext() );
        TeamIemViewBinding binding = TeamIemViewBinding.inflate(layoutInflater, viewGroup, false);
        binding.setTeamInterface(teamInterface);

        return new ViewHolder(binding);  // getRoot()で読み込んだレイアウトのViewを取得

    }

    // 取得したセルデータをViewHolderが参照してきたView(各パーツ)にセットする (1セルごとに毎回呼び出される）
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // 2022.10.28 ito　★★★RecyclerViewにDataBindingを導入してみた - Qiita　を参考に記述
        Team team = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.teamIemViewBinding.setTeam(team);
        viewHolder.teamIemViewBinding.executePendingBindings();

        //勝利数の更新ボタンのクリックハンドラーの登録　2022.11.15 ito
        //クリックした行の入力データ（勝利数）を使用する必要があるため
        // Fragmentクラス内でなくAdapterクラス内にハンドラーを記述
        viewHolder.teamIemViewBinding.btnWinUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                team.setWin( Integer.parseInt( String.valueOf( et.getText() ) ) );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamAdapter","Team:"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","onClick() position："+position);
            }
        });
        //勝利数の＋ボタンのクリックハンドラーの登録　2022.11.16 ito
        viewHolder.teamIemViewBinding.btnWinAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                int w = Integer.parseInt(String.valueOf(et.getText()))+1 ;
                // ↓無くても ViewModelが更新してくれるが更新やや遅れるため入れておく
                //et.setText( String.valueOf(w) );
                team.setWin( w );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamAdapter","勝利数の＋ボタンのクリックハンドラー  Team :"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","勝利数の＋ボタンのクリックハンドラー  position："+position);
            }
        });
        //勝利数の―ボタンのクリックハンドラーの登録　2022.11.16 ito
        viewHolder.teamIemViewBinding.btnWinDecrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                int w = Integer.parseInt(String.valueOf(et.getText()))-1 ;
                // ↓無くても ViewModelが更新してくれるが更新やや遅れるため入れておく
                //et.setText( String.valueOf(w) );
                team.setWin( w );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamAdapter","勝利数の―ボタンのクリックハンドラー  Team:"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","勝利数の―ボタンのクリックハンドラー  position："+position);
            }
        });

        //チーム名TextViewのクリックハンドラーの登録
        viewHolder.teamIemViewBinding.txtTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("★TeamAdapter","onClick Team:"+team.getName());
                teamInterface.onItemClick( team );
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TeamIemViewBinding teamIemViewBinding;
        public ViewHolder(TeamIemViewBinding m_teamIemViewBinding) {
            super(m_teamIemViewBinding.getRoot());
            this.teamIemViewBinding = m_teamIemViewBinding;
        }

    }

    public interface TeamInterface {
        void onItemClick(Team team);
        void onUpdateWins(Team team);
    }


}
