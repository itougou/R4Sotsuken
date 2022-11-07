package com.example.r4sotuskenfragmentsample1025.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.example.r4sotuskenfragmentsample1025.entity.Player;
import com.example.r4sotuskenfragmentsample1025.entity.PlayerAndTeam;
import com.example.r4sotuskenfragmentsample1025.entity.Team;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayerAndTeamDao_Impl implements PlayerAndTeamDao {
  private final RoomDatabase __db;

  public PlayerAndTeamDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public LiveData<List<PlayerAndTeam>> getAll() {
    final String _sql = "SELECT * FROM player";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"team","player"}, true, new Callable<List<PlayerAndTeam>>() {
      @Override
      public List<PlayerAndTeam> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
            final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
            final int _cursorIndexOfFaceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "face_url");
            final int _cursorIndexOfTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "team_id");
            final ArrayMap<String, Team> _collectionTeam = new ArrayMap<String, Team>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfTeamId)) {
                final String _tmpKey = _cursor.getString(_cursorIndexOfTeamId);
                _collectionTeam.put(_tmpKey, null);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipteamAscomExampleR4sotuskenfragmentsample1025EntityTeam(_collectionTeam);
            final List<PlayerAndTeam> _result = new ArrayList<PlayerAndTeam>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final PlayerAndTeam _item;
              final Player _tmpPlayer;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfHeight) && _cursor.isNull(_cursorIndexOfWeight) && _cursor.isNull(_cursorIndexOfFaceUrl) && _cursor.isNull(_cursorIndexOfTeamId))) {
                _tmpPlayer = new Player();
                final String _tmpId;
                _tmpId = _cursor.getString(_cursorIndexOfId);
                _tmpPlayer.setId(_tmpId);
                final String _tmpName;
                _tmpName = _cursor.getString(_cursorIndexOfName);
                _tmpPlayer.setName(_tmpName);
                final int _tmpHeight;
                _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
                _tmpPlayer.setHeight(_tmpHeight);
                final int _tmpWeight;
                _tmpWeight = _cursor.getInt(_cursorIndexOfWeight);
                _tmpPlayer.setWeight(_tmpWeight);
                final String _tmpFace_url;
                _tmpFace_url = _cursor.getString(_cursorIndexOfFaceUrl);
                _tmpPlayer.setFace_url(_tmpFace_url);
                final String _tmpTeam_id;
                _tmpTeam_id = _cursor.getString(_cursorIndexOfTeamId);
                _tmpPlayer.setTeam_id(_tmpTeam_id);
              }  else  {
                _tmpPlayer = null;
              }
              Team _tmpTeam = null;
              if (!_cursor.isNull(_cursorIndexOfTeamId)) {
                final String _tmpKey_1 = _cursor.getString(_cursorIndexOfTeamId);
                _tmpTeam = _collectionTeam.get(_tmpKey_1);
              }
              _item = new PlayerAndTeam();
              _item.player = _tmpPlayer;
              _item.team = _tmpTeam;
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  private void __fetchRelationshipteamAscomExampleR4sotuskenfragmentsample1025EntityTeam(final ArrayMap<String, Team> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, Team> _tmpInnerMap = new ArrayMap<String, Team>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipteamAscomExampleR4sotuskenfragmentsample1025EntityTeam(_tmpInnerMap);
          _map.putAll((Map<String, Team>) _tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, Team>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipteamAscomExampleR4sotuskenfragmentsample1025EntityTeam(_tmpInnerMap);
        _map.putAll((Map<String, Team>) _tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name` FROM `team` WHERE `id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndex(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndex(_cursor, "name");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          if (_map.containsKey(_tmpKey)) {
            final Team _item_1;
            _item_1 = new Team();
            if (_cursorIndexOfId != -1) {
              final String _tmpId;
              _tmpId = _cursor.getString(_cursorIndexOfId);
              _item_1.setId(_tmpId);
            }
            if (_cursorIndexOfName != -1) {
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _item_1.setName(_tmpName);
            }
            _map.put(_tmpKey, _item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
