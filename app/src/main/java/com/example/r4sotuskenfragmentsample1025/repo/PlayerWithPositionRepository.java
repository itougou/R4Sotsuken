package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerWithPositionDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerWithPosition;

import java.util.List;

//2022.12.6
// Player（多）Position（多）の関連付け情報のリポジトリ
public class PlayerWithPositionRepository {

    private PlayerWithPositionDao mPlayerWithPositionDao;

    public PlayerWithPositionRepository(Application application) {
        //データベースクラスのインスタンス取得
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);

        mPlayerWithPositionDao = db.PlayerWithPositionDao();    //DAO取得
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
//×       public LiveData<List<PlayerPositionAndPosition>> serchPlayerPosition2() {
//        Log.d("★P_P_A_P Repositor","getAllTeams()の中でmAllTeamsを返却");
//        Log.i("★PPAP ","this.mAllPlayerAndTeam："+this.mAllPlayerAndTeam.getValue());
//        return mPlayerPositionAndPositionDao.serchPlayerPosition();
//    }

    //2022.12.6
    public LiveData<PlayerWithPosition> serchPlayerPosition(String player_id) {
        Log.d("★P_W_P Repository","serchPlayerPosition()");

        return mPlayerWithPositionDao.serchPlayerPosition(player_id);
    }
    //2022.12.6
    public LiveData<List<PlayerWithPosition>> getAllPlayerPosition( ) {
        Log.d("★P_W_P Repository","getAllPlayerPosition()");

        return mPlayerWithPositionDao.getAll();
    }
}
