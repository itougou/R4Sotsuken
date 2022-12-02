package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.r4sotuskenfragmentsample1025.entity.PlayerPositionAndPosition;

import java.util.List;

@Dao
public interface PlayerPositionAndPositionDao {
    @Transaction
    @Query("SELECT * FROM player_position")
    LiveData<List<PlayerPositionAndPosition>> getAll();

    @Transaction
    @Query("SELECT * FROM player_position WHERE player_id = :player_id" )
    LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition( String player_id );

    @Transaction
    @Query("SELECT * FROM player_position" )
    LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition(  );
}
