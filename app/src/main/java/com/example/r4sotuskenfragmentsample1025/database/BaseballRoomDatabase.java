package com.example.r4sotuskenfragmentsample1025.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.r4sotuskenfragmentsample1025.dao.PlayerAndTeamDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerPositionAndPositionDao;
import com.example.r4sotuskenfragmentsample1025.dao.PlayerPositionDao;
import com.example.r4sotuskenfragmentsample1025.dao.TeamDao;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerPosition;
import com.example.r4sotuskenfragmentsample1025.entity.Position;
import com.example.r4sotuskenfragmentsample1025.entity.Team;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Team.class, Player.class, Position.class, PlayerPosition.class  }, version = 8,
        //最初Ver1作成時は autoMigrations はコメントにしておくこと

        //テーブルの中の値が変わったのみの場合はVerUpしないで、DeviceExplorer内のファイル削除し、.dbのファイル名を変えて読み込ませること。
        //どうしてもうまくいかなくなった場合はEmuratorを削除し、作成しなおすと良い

        //テーブル名を変更したときは、DeviceExplorer内の.dbファイル削除、versionアップ、以前のバージョンの@AutoMigrationコメント化
        //assertへ変更後のdbファイル設置（ファイル名も変えて）で読み込めた。
        autoMigrations = {
//                @AutoMigration (from = 1, to = 2),
//                @AutoMigration (from = 2, to = 3),
//                @AutoMigration (from = 3, to = 4),
//                @AutoMigration (from = 4, to = 5),
//                @AutoMigration (from = 5, to = 6),
//                @AutoMigration (from = 6, to = 7),
                @AutoMigration (from = 7, to = 8)
        },
        exportSchema = true
)

abstract public class BaseballRoomDatabase  extends RoomDatabase {

    public abstract PlayerDao PlayerDao();
    public abstract TeamDao TeamDao();
    public abstract PlayerAndTeamDao PlayerAndTeamDao();
    //2022.11.29 ito
    public abstract PlayerPositionAndPositionDao PlayerPositionAndPositionDao();
    public abstract PlayerPositionDao PlayerPositionDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile BaseballRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Migration path definition from version 2 to version 3.
//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE team "
//                    + " ADD COLUMN win INTEGER");
//            database.execSQL("ALTER TABLE team "
//                    + " ADD COLUMN losing INTEGER");
//        }
//    };

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

//2022.11.14 ito
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                                    BaseballRoomDatabase.class, "BaseballTeamDB.db")
//                            .addCallback(sRoomDatabaseCallback).createFromAsset("BaseballTeamDB2.db")
//                            .addMigrations(MIGRATION_1_2)
//                            .fallbackToDestructiveMigration()
//                            .build();

                    //新たなファイルを読み込ませるには、createFromAsset("BaseballTeamDB2.db")のみ変更すること
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BaseballRoomDatabase.class, "BaseballTeamDB.db")
                            .addCallback(sRoomDatabaseCallback).createFromAsset("BaseballTeamDB12.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     * プログラム中でDBに初期値を設定したい場合に使用する。現在未使用。
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
