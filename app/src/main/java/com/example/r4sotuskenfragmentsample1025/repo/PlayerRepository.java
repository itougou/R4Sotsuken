package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerDao;
import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;

//現在未使用
public class PlayerRepository {
    private LiveData<List<Player>> mAllPlayers;
    private PlayerDao mPlayerDao;

    public PlayerRepository(Application application) {
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);
        mPlayerDao = db.PlayerDao();
        this.mAllPlayers = mPlayerDao.getAlphabetizedPlayer();
        Log.d("★PlayerRepository","PlayerRepository() mPlayerDao.getAlphabetizedPlayer()呼び出し ﾌｨｰﾙﾄﾞmAllPlayersへ格納:"+mAllPlayers.getValue());

        //this.mAllTeams = mAllTeams;
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Player>> getAllPlayers() {
        Log.d("★PlayerRepository","getAllPlayerms()の中でmAllPlayersを返却");
        return mAllPlayers;
    }
}
