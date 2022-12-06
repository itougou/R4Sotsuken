package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.r4sotuskenfragmentsample1025.entity.PlayerWithPosition;

import java.util.List;

@Dao
public interface PlayerWithPositionDao {
    @Transaction
    @Query("SELECT * FROM player order by player_id")
    LiveData<List<PlayerWithPosition>> getAll();

    @Transaction
    @Query("SELECT * FROM player WHERE player_id = :player_id" )
    LiveData<PlayerWithPosition> serchPlayerPosition( String player_id );

}
