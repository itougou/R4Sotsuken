package com.example.r4sotuskenfragmentsample1025.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import com.example.r4sotuskenfragmentsample1025.repo.PlayerAndTeamRepository;
import com.example.r4sotuskenfragmentsample1025.repo.PlayerRepository;
import com.example.r4sotuskenfragmentsample1025.repo.TeamRepository;

import java.util.List;

public class BaseballViewModel  extends AndroidViewModel {

    private TeamRepository mRepository;
    private PlayerAndTeamRepository mRepository2;
    private PlayerRepository mRepository3;


    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    //private final LiveData<List<Word>> mAllWords;

    //LiveData
    private final LiveData<List<Team>> mAllTeams;   //チーム一覧表示（ThirdFragment)で使用
    private final LiveData<List<Player>> mAllPlayers;  //現在未使用
    private final LiveData<List<PlayerAndTeam>> mAllPlayerAndTeams; //選手名＆チーム一覧表示（ThirdFragment)で使用

    //MutableData
    MutableLiveData<PlayerAndTeam> mutablePlayer = new MutableLiveData<>(); //選手詳細情報画面（FourthFragment)表示で使用

    //2022.10.24 ito
    //public LiveData<String> mSearchedData;

    public BaseballViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new TeamRepository(application);
        Log.d("★BaseballViewModel","BaseballViewModel() mRepository.getAllTeams()呼び出し");
        mAllTeams = mRepository.getAllTeams();

        this.mRepository2 = new PlayerAndTeamRepository(application);
        Log.d("★BaseballViewModel","BaseballViewModel() mRepository2.getAll()呼び出し");
        mAllPlayerAndTeams = mRepository2.getAll();

        this.mRepository3 = new PlayerRepository(application);
        Log.d("★BaseballViewModel","BaseballViewModel() mRepository3.getAllPlayers()呼び出し");
        mAllPlayers = mRepository3.getAllPlayers();

    }

    public LiveData<List<Team>> getAllTeams() {
        Log.d("★BaseballViewModel","getAllWords() の中でmAllTeamsを返却");
        return mAllTeams;
    }
    public LiveData<List<PlayerAndTeam>> getAll() {
        Log.d("★BaseballViewModel","getAll() の中でmAllPlayerAndTeamsを返却");
        return mAllPlayerAndTeams;
    }
    public LiveData<List<Player>> getAllPlayers() {
        Log.d("★BaseballViewModel","getPlayer() の中でmAllPlayerを返却"+mAllPlayers.getValue());
        return mAllPlayers;
    }
    //2022.10.29 ito
    public void setPlayerAndTeam(PlayerAndTeam playerAndTeam){
        mutablePlayer.setValue(playerAndTeam);
    }
    public PlayerAndTeam getPlayerAndTeam(){
        return mutablePlayer.getValue();
    }
}
