package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * ポジション（守備位置情報）マスターテーブル のエンティティクラス
 */
@Entity(tableName = "position")
public class Position {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;  //ポジションID
    private String name;    //ポジション名
    private String jp_name; //日本語名

    public Position(String id, String name, String jp_name) {
        this.id = id;
        this.name = name;
        this.jp_name = jp_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
