package com.example.r4sotuskenfragmentsample1025.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r4sotuskenfragmentsample1025.BR;
import com.example.r4sotuskenfragmentsample1025.databinding.PlayerItemViewBinding;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
public class ItemAdapter  extends ListAdapter<PlayerAndTeam,ItemAdapter.ViewHolder> {

    private String[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //2022.10.28 for DataBind private final TextView textView;
        private ViewDataBinding viewDataBinding;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //2022.10.28 for DataBind textView = (TextView) view.findViewById(R.id.txtPlayer);
            viewDataBinding = DataBindingUtil.bind(view); // 引数ActivityのときはsetContentView()だが引数Viewのときはbind()

        }

        //2022.10.28 for DataBind
//        public TextView getTextView() {
//            return textView;
//        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
//    public ItemAdapter(String[] dataSet) {
//
//        localDataSet = dataSet;
//    }

// 2022.10.29 ito
//    public ItemAdapter(@NonNull DiffUtil.ItemCallback<Player> diffCallback) {
//
//        super(diffCallback);
//    }

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
        // Create a new view, which defines the UI of the list item
        //2022.10.28 for Databind
        //View view = LayoutInflater.from(viewGroup.getContext())
        //        .inflate(R.layout.player_item_view, viewGroup, false);
        //return new ViewHolder(view);

        LayoutInflater layoutInflater = LayoutInflater.from( viewGroup.getContext() );
        PlayerItemViewBinding binding = PlayerItemViewBinding.inflate(layoutInflater, viewGroup, false);

        //2022.10.29 ito
        binding.setPlayerInterface(playerInterface);

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
        PlayerAndTeam playerAndTeam = getItem(position);  // 全セルデータからx番目のデータを取得
        viewHolder.getViewDataBinding().setVariable(BR.playerAndTeam, playerAndTeam);

        // ShoppingCartのソースを参考に記述したが、setPalyer()が見つからず、コンパイル通らない
        //viewHolder.viewDataBinding.setPlayer(player);
        //viewHolder.viewDataBinding.executePendingBindings();
    }

    //2022.10.28 ito
    // Return the size of your dataset (invoked by the layout manager)
    //  @Override
    //    public int getItemCount() {
    //        return localDataSet.length;
    //    }


    //2022.10.28
    public static class ItemDiff extends DiffUtil.ItemCallback<Player> {

        @Override
        public boolean areItemsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }
    public interface PlayerInterface {
        void onItemClick(PlayerAndTeam playerAndTeam);
    }
}
