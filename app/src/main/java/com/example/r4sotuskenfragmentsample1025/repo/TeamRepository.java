package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;

public class TeamRepository {
    private LiveData<List<Team>> mAllTeams;
    private TeamDao mTeamDao;

    public TeamRepository(Application application) {
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);
        mTeamDao = db.TeamDao();
        this.mAllTeams = mTeamDao.getAlphabetizedTeam();
        Log.d("★TeamRepository","TeamRepository() mTeamDao.getAlphabetizedTeams()呼び出し ﾌｨｰﾙﾄﾞmAllWordsへ格納:"+mAllTeams.getValue());

        //this.mAllTeams = mAllTeams;
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Team>> getAllTeams() {
        Log.d("★TeamRepository","getAllTeams()の中でmAllTeamsを返却");
        return mAllTeams;
    }
}
