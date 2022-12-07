package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerPositionAndPositionDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerPositionAndPosition;

import java.util.List;
// PlayerPosition（１）Position（１）の関連付け情報のリポジトリ
public class PlayerPositionAndPositionRepository {

    private PlayerPositionAndPositionDao mPlayerPositionAndPositionDao;
    //2022.12.6
    //private PlayerPositionWithPositionDao mPlayerPositionWithPositionDao;

    public PlayerPositionAndPositionRepository(Application application) {
        //データベースクラスのインスタンス取得
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);

        mPlayerPositionAndPositionDao = db.PlayerPositionAndPositionDao();  //DAO取得
        //2022.12.6
        //mPlayerPositionWithPositionDao = db.PlayerPositionWithPositionDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition( String player_id) {
        Log.d("★H_P_A_P Repository","serchPlayerPosition()の中選手＋ポジション情報Listを返却");

        return mPlayerPositionAndPositionDao.serchPlayerPosition(player_id);
    }

//×       public LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition2() {
//        Log.d("★P_P_A_P Repositor","getAllTeams()の中でmAllTeamsを返却");
//        Log.i("★PPAP ","this.mAllPlayerAndTeam："+this.mAllPlayerAndTeam.getValue());
//        return mPlayerPositionAndPositionDao.serchPlayerPosition();
//    }
    //2022.12.5
    public LiveData<List<PlayerPositionAndPosition>> getAllPlayerPosition( ) {
        Log.d("★H_P_A_P Repository","serchPlayerPosition()の中選手＋ポジション情報Listを返却");

        return mPlayerPositionAndPositionDao.getAll();
    }

}
