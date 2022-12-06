package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/*
 * PlayerPosition（１） と Position（１） の１対１ の関連づけ  のエンティティクラス
 */
public class PlayerPositionAndPosition {
    @Embedded
    public PlayerPosition playerPosition;   //主のテーブル＝player_positionテーブル

    @Relation( parentColumn = "position_id", entityColumn = "position_id")
    public Position position; //従のテーブル＝positionテーブル

    public String getPlayer_id() {
        return playerPosition.getPlayer_id();
    }
    public String getPosition_id() {
        return playerPosition.getPosition_id();
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static DiffUtil.ItemCallback<PlayerPositionAndPosition> itemCallback = new DiffUtil.ItemCallback<PlayerPositionAndPosition>() {
        @Override
        public boolean areItemsTheSame(@NonNull PlayerPositionAndPosition oldItem, @NonNull PlayerPositionAndPosition newItem) {
            return oldItem.getPlayer_id().equals(newItem.getPlayer_id()) &&
                    oldItem.getPosition_id().equals(newItem.getPosition_id());
        }
        @Override
        public boolean areContentsTheSame(@NonNull PlayerPositionAndPosition oldItem, @NonNull PlayerPositionAndPosition newItem) {
            return oldItem.equals(newItem);
        }
    };

}
