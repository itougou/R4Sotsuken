package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerAndTeamDao;
import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;

public class PlayerAndTeamRepository {
    private LiveData<List<PlayerAndTeam>> mAllPlayerAndTeam;
    private LiveData<PlayerAndTeam> mSearchedPlayerAndTeam;
    private PlayerAndTeamDao mPlayerAndTeamDao;

    public PlayerAndTeamRepository(Application application) {
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);
        mPlayerAndTeamDao = db.PlayerAndTeamDao();
        this.mAllPlayerAndTeam = mPlayerAndTeamDao.getAll();
        Log.d("★TeamRepository","TeamRepository() mTeamDao.getAlphabetizedTeams()呼び出し ﾌｨｰﾙﾄﾞmAllWordsへ格納:"+mAllPlayerAndTeam.toString());

    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PlayerAndTeam>> getAll() {
        Log.d("★TeamRepository","getAllTeams()の中でmAllTeamsを返却");
        return this.mAllPlayerAndTeam;
    }
    public LiveData<PlayerAndTeam> searchPlayer() {
        Log.d("★TeamRepository","searchPlayer(()の中でmSearchedPlayer(を返却");
        return this.mSearchedPlayerAndTeam;
    }
}
