package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.r4sotuskenfragmentsample1025.entity.PlayerPosition;

import java.util.List;

@Dao
public interface PlayerPositionDao {
    @Query("SELECT * FROM player_position ORDER BY player_id ASC")
    LiveData<List<PlayerPosition>> getPlayerPosition();
}
