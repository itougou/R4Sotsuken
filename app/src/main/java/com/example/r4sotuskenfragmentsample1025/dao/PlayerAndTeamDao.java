package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;
@Dao
public interface PlayerAndTeamDao {
    @Transaction
    @Query("SELECT * FROM player")
    LiveData<List<PlayerAndTeam>> getAll();

    @Transaction
    @Query("SELECT * FROM player WHERE  player.id = :id")
    LiveData<PlayerAndTeam> serchPlayer( String id);
}
