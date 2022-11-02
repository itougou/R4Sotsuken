package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.r4sotuskenfragmentsample1025.entity.Player;

import java.util.List;
@Dao
public interface PlayerDao {
    @Query("SELECT * FROM player ORDER BY name ASC")
    LiveData<List<Player>> getAlphabetizedPlayer();
}
