package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerAndTeamDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerPositionAndPositionDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerPositionDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerPositionAndPosition;

import java.util.List;

public class PlayerPositionAndPositionRepository {
    private LiveData<List<PlayerPositionAndPosition>> mAllPlayerPositionAndPosition;
    private LiveData<PlayerAndTeam> mSearchedPlayerAndTeam;
    private PlayerPositionAndPositionDao mPlayerPositionAndPositionDao;
    private PlayerPositionDao mPlayerPositionDao;
    private PlayerAndTeamDao mPlayerAndTeamDao;
    private LiveData<List<PlayerAndTeam>> mAllPlayerAndTeam;

    public PlayerPositionAndPositionRepository(Application application) {
        //データベースクラスのインスタンス取得
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);

        mPlayerPositionAndPositionDao = db.PlayerPositionAndPositionDao();  //DAO取得
        mPlayerPositionDao = db.PlayerPositionDao();  //DAO取得

        mPlayerAndTeamDao = db.PlayerAndTeamDao();  //DAO取得
        this.mAllPlayerAndTeam = mPlayerAndTeamDao.getAll();    //全選手＆チーム情報取得
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition( String player_id) {
        Log.d("★H_P_A_P Repository","serchPlayerPosition()の中選手＋ポジション情報Listを返却");

        return mPlayerPositionAndPositionDao.serchPlayerPosition(player_id);
    }

//    public LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition2() {
//        Log.d("★P_P_A_P Repositor","getAllTeams()の中でmAllTeamsを返却");
//        Log.i("★PPAP ","this.mAllPlayerAndTeam："+this.mAllPlayerAndTeam.getValue());
//        return mPlayerPositionAndPositionDao.serchPlayerPosition();
//    }


}
