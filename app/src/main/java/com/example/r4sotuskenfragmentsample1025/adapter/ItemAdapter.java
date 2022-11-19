package com.example.r4sotuskenfragmentsample1025.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.databinding.PlayerItemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
public class ItemAdapter  extends ListAdapter<PlayerAndTeam,ItemAdapter.ViewHolder> {

    private String[] localDataSet;//未使用

    //2022.10.29 ito  コンストラクタ追加 for Click ハンドラー
    PlayerInterface playerInterface;
    public ItemAdapter(PlayerInterface playerInterface) {
        super(PlayerAndTeam.itemCallback);
        this.playerInterface = playerInterface;
    }

    // セルのレイアウトを読み込んでViewHolderと紐付ける (1セルごとに毎回呼び出される）
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( viewGroup.getContext() );
        PlayerItemViewBinding binding = PlayerItemViewBinding.inflate(layoutInflater, viewGroup, false);
        binding.setPlayerInterface(playerInterface);

        return new ViewHolder(binding);
    }

    // 取得したセルデータをViewHolderが参照してきたView(各パーツ)にセットする (1セルごとに毎回呼び出される）
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PlayerAndTeam playerAndTeam = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.playerItemViewBinding.setPlayerAndTeam(playerAndTeam);
        viewHolder.playerItemViewBinding.executePendingBindings();

        viewHolder.playerItemViewBinding.txtPlayer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                playerInterface.onItemClick(playerAndTeam);
                Log.d("★ItemAdapter","txtPlayer onClick");
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PlayerItemViewBinding playerItemViewBinding;
        public ViewHolder(PlayerItemViewBinding m_playerItemViewBinding) {
            super(m_playerItemViewBinding.getRoot());
            this.playerItemViewBinding = m_playerItemViewBinding;
       }
    }

    public interface PlayerInterface {
        void onItemClick(PlayerAndTeam playerAndTeam);
    }
}
