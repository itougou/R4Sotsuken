package com.example.r4sotuskenfragmentsample1025.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r4sotuskenfragmentsample1025.BR;
import com.example.r4sotuskenfragmentsample1025.R;
import com.example.r4sotuskenfragmentsample1025.databinding.TeamIemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.view.TeamListFragment;
import com.example.r4sotuskenfragmentsample1025.viewmodel.BaseballViewModel;

public class TeamAdapter extends ListAdapter<Team,TeamAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //2022.10.28 for DataBind private final TextView textView;
        private ViewDataBinding viewDataBinding;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //2022.10.28 for DataBind textView = (TextView) view.findViewById(R.id.txtPlayer);
            viewDataBinding = DataBindingUtil.bind(view); // 引数ActivityのときはsetContentView()だが引数Viewのときはbind()

        }
        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }

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

        //2022.10.29 ito
        binding.setTeamInterface(teamInterface);

        return new ViewHolder(binding.getRoot());  // getRoot()で読み込んだレイアウトのViewを取得

    }

    // 取得したセルデータをViewHolderが参照してきたView(各パーツ)にセットする (1セルごとに毎回呼び出される）
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.getTextView().setText(localDataSet[position]);//★DataBindを使うと不要になる

        // 2022.10.28 ito　★★★RecyclerViewにDataBindingを導入してみた - Qiita　を参考に記述
        Team team = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.getViewDataBinding().setVariable(BR.Team, team);

        // ShoppingCartのソースを参考に記述したが、setPalyer()が見つからず、コンパイル通らない
        //viewHolder.viewDataBinding.setPlayer(player);
        //viewHolder.viewDataBinding.executePendingBindings();

        //勝利数の更新ボタンのクリックハンドラーの登録　2022.11.15 ito
        //クリックした行の入力データ（勝利数）を使用する必要があるため
        // Fragmentクラス内でなくAdapterクラス内にハンドラーを記述
        viewHolder.itemView.findViewById(R.id.btnWinUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                team.setWin( Integer.parseInt( String.valueOf( et.getText() ) ) );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamListFragment","onUpdateWins() Team mae:"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","onClick() position："+position);
            }
        });
        //勝利数の＋ボタンのクリックハンドラーの登録　2022.11.16 ito
        viewHolder.itemView.findViewById(R.id.btnWinAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                int w = Integer.parseInt(String.valueOf(et.getText()))+1 ;
                // ↓無くても ViewModelが更新してくれるが更新やや遅れるため入れておく
                et.setText( String.valueOf(w) );
                team.setWin( w );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamListFragment","勝利数の＋ボタンのクリックハンドラー onUpdateWins() Team mae:"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","勝利数の＋ボタンのクリックハンドラー  position："+position);
            }
        });
        //勝利数の―ボタンのクリックハンドラーの登録　2022.11.16 ito
        viewHolder.itemView.findViewById(R.id.btnWinDecrese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = viewHolder.itemView.findViewById(R.id.editTextNumberDecimal);
                int w = Integer.parseInt(String.valueOf(et.getText()))-1 ;
                // ↓無くても ViewModelが更新してくれるが更新やや遅れるため入れておく
                et.setText( String.valueOf(w) );
                team.setWin( w );

                teamInterface.onUpdateWins( team );

                Log.i("★TeamListFragment","勝利数の―ボタンのクリックハンドラー onUpdateWins() Team mae:"+team.getName()+",Team.win:"+team.getWin());
                Log.d("★TeamAdapter","勝利数の―ボタンのクリックハンドラー  position："+position);
            }
        });
    }

    //2022.10.28
    public static class TeamDiff extends DiffUtil.ItemCallback<Team> {

        @Override
        public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

    public interface TeamInterface {
        void onItemClick(Team team);
        void onUpdateWins(Team team);
    }


}
