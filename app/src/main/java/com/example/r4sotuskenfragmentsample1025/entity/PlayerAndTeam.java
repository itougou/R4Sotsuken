package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

public class PlayerAndTeam {
    @Embedded
    public Player player;

    @Relation( parentColumn = "team_id", entityColumn = "id")
    public Team team;

    //2022.10.29 ito
    public String getId() {
        return player.getId();
    }

    //2022.10.29 ito
    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static DiffUtil.ItemCallback<PlayerAndTeam> itemCallback = new DiffUtil.ItemCallback<PlayerAndTeam>() {
        @Override
        public boolean areItemsTheSame(@NonNull PlayerAndTeam oldItem, @NonNull PlayerAndTeam newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlayerAndTeam oldItem, @NonNull PlayerAndTeam newItem) {
            return oldItem.equals(newItem);
        }
    };

}
