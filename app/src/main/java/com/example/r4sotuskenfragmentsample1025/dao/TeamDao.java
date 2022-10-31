package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;
@Dao
public interface TeamDao {
    @Query("SELECT * FROM team ORDER BY name ASC")
    LiveData<List<Team>> getAlphabetizedTeam();
}
