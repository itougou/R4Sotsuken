package com.example.r4sotuskenfragmentsample1025.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.database.BaseballRoomDatabase;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.List;
import java.util.Random;

public class TeamRepository {
    private LiveData<List<Team>> mAllTeams;
    private TeamDao mTeamDao;
    Application mAapplication;

    public TeamRepository(Application application) {
        mAapplication = application;    //後でINSERTで使うのでフィールドにとっておく
        //DBクラスのインスタンス取得
        BaseballRoomDatabase db = BaseballRoomDatabase.getDatabase(application);

        mTeamDao = db.TeamDao();    //DAO生成
        this.mAllTeams = mTeamDao.getAlphabetizedTeam();    //全チーム情報取得
        Log.d("★TeamRepository","TeamRepository() mTeamDao.getAlphabetizedTeams()呼び出し ﾌｨｰﾙﾄﾞmAllWordsへ格納:"+mAllTeams.getValue());

    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    //全チーム情報取り出し用メソッド
    public LiveData<List<Team>> getAllTeams() {
        Log.d("★TeamRepository","getAllTeams()の中でmAllTeamsを返却");
        return mAllTeams;
    }

    //チーム１件追加メソッド 2022.11.1
    public void insertTeam( Team team ){
        //INSERT文は、普通に実行するとエラーになるので、別スレッドで実行
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTeamDao.insertTeam( team );
                Log.d("★TeamRepository","insertTeam()->run()-> insertTeam()");
            }
        }).start();
    }
}
