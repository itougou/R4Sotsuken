package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * ポジション（守備位置情報）マスターテーブル のエンティティクラス
 */
@Entity(tableName = "position")
public class Position {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String position_id;  //ポジションID
    private String name;    //ポジション名
    private String jp_name; //日本語名

    public Position(String position_id, String name, String jp_name) {
        this.position_id = position_id;
        this.name = name;
        this.jp_name = jp_name;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJp_name() {
        return jp_name;
    }

    public void setJp_name(String jp_name) {
        this.jp_name = jp_name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static DiffUtil.ItemCallback<Position> itemCallback = new DiffUtil.ItemCallback<Position>() {
        @Override
        public boolean areItemsTheSame(@NonNull Position oldItem, @NonNull Position newItem) {
            return oldItem.getPosition_id().equals(newItem.getPosition_id()) &&
                    oldItem.getPosition_id().equals(newItem.getPosition_id());
        }
        @Override
        public boolean areContentsTheSame(@NonNull Position oldItem, @NonNull Position newItem) {
            return oldItem.equals(newItem);
        }
    };
}
