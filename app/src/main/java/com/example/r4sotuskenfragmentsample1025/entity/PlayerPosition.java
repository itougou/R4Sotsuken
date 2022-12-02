package com.example.r4sotuskenfragmentsample1025.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.RenameTable;

/*
 * 「選手とポジションの組み合わせを管理するテーブル」 のエンティティクラス
 */

@Entity(tableName="player_position", primaryKeys = {"player_id", "position_id"})
public class PlayerPosition {

    @NonNull
    private String player_id;

    @NonNull
    private String position_id;

    public PlayerPosition(String player_id, String position_id) {
        this.player_id = player_id;
        this.position_id = position_id;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }
}
