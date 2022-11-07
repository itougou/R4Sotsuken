package com.example.r4sotuskenfragmentsample1025.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerAndTeamDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerDao;
import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Team.class, Player.class  }, version = 1, exportSchema = false)
abstract public class BaseballRoomDatabase  extends RoomDatabase {

    public abstract PlayerDao PlayerDao();
    public abstract TeamDao TeamDao();
    public abstract PlayerAndTeamDao PlayerAndTeamDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile BaseballRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BaseballRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BaseballRoomDatabase.class) {
                if (INSTANCE == null) {
//2022.10.24 ito
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            WordRoomDatabase.class, "word_database")
//                            .addCallback(sRoomDatabaseCallback)
//                            .build();
                    //java → assetsフォルダに置いたSQLiteのDBファイルを読み込む
                    //DeviceFileExplore → data → data → パッケージ名 → Databases にデータが保管されれる
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BaseballRoomDatabase.class, "BaseballTeamDB.db")
                            .addCallback(sRoomDatabaseCallback).createFromAsset("BaseballTeamDB.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     * 現在未使用
     */
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
//2022.10.24 ito
//                WordDao dao = INSTANCE.wordDao();
//                dao.deleteAll();
//
//                Word word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);

                Log.d("★RoomDatabase.Callback：","★");
            });
        }
    };

}
