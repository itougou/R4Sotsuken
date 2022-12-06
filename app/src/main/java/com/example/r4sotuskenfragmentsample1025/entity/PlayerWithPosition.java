package com.example.r4sotuskenfragmentsample1025.entity;

import android.icu.text.Transliterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/*
 * PlayerPosition（多） と Position（多） の多対多 の関連づけ  のエンティティクラス
 */
public class PlayerWithPosition {
    @Embedded
    public Player player;   //主のテーブル＝player_positionテーブル
    @Relation(
            parentColumn = "player_id",
            entityColumn = "position_id",
            associateBy = @Junction(PlayerPosition.class)
    )
    public List<Position> positions; //従のテーブル＝positionテーブル

    public String getPlayer_id() {
        return player.getPlayer_id();
    }
//    //public String getPosition_id() {
//        return playerPosition.getPosition_id();
//    }
    public List<Position> getPositions() {
        return positions;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static DiffUtil.ItemCallback<PlayerWithPosition> itemCallback = new DiffUtil.ItemCallback<PlayerWithPosition>() {
        @Override
        public boolean areItemsTheSame(@NonNull PlayerWithPosition oldItem, @NonNull PlayerWithPosition newItem) {
            return oldItem.getPlayer_id().equals(newItem.getPlayer_id()) &&
                    oldItem.getPlayer_id().equals(newItem.getPlayer_id());
        }
        @Override
        public boolean areContentsTheSame(@NonNull PlayerWithPosition oldItem, @NonNull PlayerWithPosition newItem) {
            return oldItem.equals(newItem);
        }
    };

}
