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
        Log.d("★TeamRepository","getAllTeams()の中でmAllTeamsを返却"+mAllTeams);
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
        }).start();;
        //this.mAllTeams = mTeamDao.getAlphabetizedTeam();    //全チーム情報取得 Room＆LiveDataが自動でDBの変更を監視＆通知してくれるため不要
    }
    //チーム１件追加メソッド 2022.11.1
    public void updateTeam( Team team ){
        //INSERT文は、普通に実行するとエラーになるので、別スレッドで実行
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTeamDao.updateTeam( team );
                Log.d("★TeamRepository","updateTeam()->run()-> updateTeam() team:"+team.getName() );
            }
        }).start();
        //this.mAllTeams = mTeamDao.getAlphabetizedTeam();    //全チーム情報取得 Room＆LiveDataが自動でDBの変更を監視＆通知してくれるため不要

        Log.d("★TeamRepository","updateTeam() mTeamDao.getAlphabetizedTeams()呼び出し ﾌｨｰﾙﾄﾞmAllWordsへ格納:"+mAllTeams.getValue());

    }
    //チーム１件追加メソッド 2022.11.1
    public void updateTeam( Team team ){
        //INSERT文は、普通に実行するとエラーになるので、別スレッドで実行
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTeamDao.updateTeam( team );
                Log.d("★TeamRepository","updateTeam()->run()-> updateTeam() team:"+team.getName() );
            }
        }).start();
    }
}
