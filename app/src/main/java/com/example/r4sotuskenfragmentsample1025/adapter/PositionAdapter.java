package com.example.r4sotuskenfragmentsample1025.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r4sotuskenfragmentsample1025.databinding.PositionItemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerPositionAndPosition;
/*
 * 選手のポジション（守備位置）の情報ををRecyclerView（position_item_vew.xml）へ結び付ける為のクラス
 */
public class PositionAdapter extends ListAdapter<PlayerPositionAndPosition, PositionAdapter.ViewHolder> {

//    public PositionAdapter(@NonNull DiffUtil.ItemCallback<HandlePositionAndPosition> diffCallback) {
//        super(diffCallback);
//    }

    PositionInterface positionInterface;
    public PositionAdapter(PositionInterface positionInterface) {
        super(PlayerPositionAndPosition.itemCallback);
        this.positionInterface = positionInterface;
    }

    // セルのレイアウトを読み込んでViewHolderと紐付ける (1セルごとに毎回呼び出される）
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( viewGroup.getContext() );
        PositionItemViewBinding binding = PositionItemViewBinding.inflate(layoutInflater, viewGroup, false);
        binding.setPositionInterface( positionInterface );

        return new ViewHolder(binding);
    }

    // 取得したセルデータをViewHolderが参照してきたView(各パーツ)にセットする (1セルごとに毎回呼び出される）
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PlayerPositionAndPosition playerPositionAndPosition = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.positionItemViewBinding.setPlayerPositionAndPosition( playerPositionAndPosition );
        viewHolder.positionItemViewBinding.executePendingBindings();

//        viewHolder.playerItemViewBinding.txtPlayer.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                playerInterface.onItemClick(playerAndTeam);
//                Log.d("★ItemAdapter","txtPlayer onClick");
//            }
//        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PositionItemViewBinding positionItemViewBinding;
        public ViewHolder(PositionItemViewBinding m_positionItemViewBinding) {
            super(m_positionItemViewBinding.getRoot());
            this.positionItemViewBinding = m_positionItemViewBinding;
       }
    }

    public interface PositionInterface {
        void onItemClick(PlayerPositionAndPosition playerPositionAndPosition );
    }
}
