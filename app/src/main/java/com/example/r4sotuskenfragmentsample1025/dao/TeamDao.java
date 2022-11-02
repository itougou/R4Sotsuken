package com.example.r4sotuskenfragmentsample1025.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;
@Dao
public interface TeamDao {
    @Query("SELECT * FROM team ORDER BY name ASC")
    LiveData<List<Team>> getAlphabetizedTeam();

    @Insert
    public void insertTeam(Team team);

    @Update
    public void updateTeam(Team team);
}

