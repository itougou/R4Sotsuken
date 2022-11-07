package com.example.r4sotuskenfragmentsample1025.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayerDao_Impl implements PlayerDao {
  private final RoomDatabase __db;

  public PlayerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public LiveData<List<Player>> getAlphabetizedPlayer() {
    final String _sql = "SELECT * FROM player ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"player"}, false, new Callable<List<Player>>() {
      @Override
      public List<Player> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfFaceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "face_url");
          final int _cursorIndexOfTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "team_id");
          final List<Player> _result = new ArrayList<Player>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Player _item;
            _item = new Player();
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final int _tmpHeight;
            _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
            _item.setHeight(_tmpHeight);
            final int _tmpWeight;
            _tmpWeight = _cursor.getInt(_cursorIndexOfWeight);
            _item.setWeight(_tmpWeight);
            final String _tmpFace_url;
            _tmpFace_url = _cursor.getString(_cursorIndexOfFaceUrl);
            _item.setFace_url(_tmpFace_url);
            final String _tmpTeam_id;
            _tmpTeam_id = _cursor.getString(_cursorIndexOfTeamId);
            _item.setTeam_id(_tmpTeam_id);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
