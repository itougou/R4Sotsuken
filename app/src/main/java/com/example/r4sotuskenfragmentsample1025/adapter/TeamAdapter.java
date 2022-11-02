package com.example.r4sotuskenfragmentsample1025.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r4sotuskenfragmentsample1025.BR;
import com.example.r4sotuskenfragmentsample1025.databinding.TeamIemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.view.TeamListFragment;

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
        Team Team = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.getViewDataBinding().setVariable(BR.Team, Team);

        // ShoppingCartのソースを参考に記述したが、setPalyer()が見つからず、コンパイル通らない
        //viewHolder.viewDataBinding.setPlayer(player);
        //viewHolder.viewDataBinding.executePendingBindings();
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
    }
}
