package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="team")
public class Team {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //2022.11.1
    public Team(@NonNull String id, String name) {
        this.id = id;
        this.name = name;
    }

    //2022.11.1
    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
    public static DiffUtil.ItemCallback<Team> itemCallback = new DiffUtil.ItemCallback<Team>() {
        @Override
        public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.equals(newItem);
        }
    };
}
