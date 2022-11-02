package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerAndTeamDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;

import java.util.List;

public class PlayerAndTeamRepository {
    private LiveData<List<PlayerAndTeam>> mAllPlayerAndTeam;
    private LiveData<PlayerAndTeam> mSearchedPlayerAndTeam;
    private PlayerAndTeamDao mPlayerAndTeamDao;

    public PlayerAndTeamRepository(Application application) {
        //データベースクラスのインスタンス取得
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);

        mPlayerAndTeamDao = db.PlayerAndTeamDao();  //DAO取得
        this.mAllPlayerAndTeam = mPlayerAndTeamDao.getAll();    //全選手＆チーム情報取得
        Log.d("★TeamRepository","TeamRepository() mTeamDao.getAlphabetizedTeams()呼び出し ﾌｨｰﾙﾄﾞmAllWordsへ格納:"+mAllPlayerAndTeam.toString());

    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    // 全選手＆チーム情報取り出し用メソッド
    public LiveData<List<PlayerAndTeam>> getAll() {
        Log.d("★TeamRepository","getAllTeams()の中でmAllTeamsを返却");
        return this.mAllPlayerAndTeam;
    }

    //現在未使用
    //    public LiveData<PlayerAndTeam> searchPlayer() {
    //        Log.d("★TeamRepository","searchPlayer(()の中でmSearchedPlayer(を返却");
    //        return this.mSearchedPlayerAndTeam;
    //    }
}
