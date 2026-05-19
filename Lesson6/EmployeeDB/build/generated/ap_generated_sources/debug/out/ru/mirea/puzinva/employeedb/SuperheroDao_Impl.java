package ru.mirea.puzinva.employeedb;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class SuperheroDao_Impl implements SuperheroDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Superhero> __insertAdapterOfSuperhero;

  private final EntityDeleteOrUpdateAdapter<Superhero> __deleteAdapterOfSuperhero;

  private final EntityDeleteOrUpdateAdapter<Superhero> __updateAdapterOfSuperhero;

  public SuperheroDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfSuperhero = new EntityInsertAdapter<Superhero>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `superheroes` (`id`,`name`,`superpower`,`strengthLevel`,`isVillain`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Superhero entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getSuperpower() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getSuperpower());
        }
        statement.bindLong(4, entity.getStrengthLevel());
        final int _tmp = entity.isVillain() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__deleteAdapterOfSuperhero = new EntityDeleteOrUpdateAdapter<Superhero>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `superheroes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Superhero entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSuperhero = new EntityDeleteOrUpdateAdapter<Superhero>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `superheroes` SET `id` = ?,`name` = ?,`superpower` = ?,`strengthLevel` = ?,`isVillain` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, final Superhero entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getSuperpower() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getSuperpower());
        }
        statement.bindLong(4, entity.getStrengthLevel());
        final int _tmp = entity.isVillain() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public void insert(final Superhero superhero) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __insertAdapterOfSuperhero.insert(_connection, superhero);
      return null;
    });
  }

  @Override
  public void insertAll(final Superhero... superheroes) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __insertAdapterOfSuperhero.insert(_connection, superheroes);
      return null;
    });
  }

  @Override
  public void delete(final Superhero superhero) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __deleteAdapterOfSuperhero.handle(_connection, superhero);
      return null;
    });
  }

  @Override
  public void update(final Superhero superhero) {
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      __updateAdapterOfSuperhero.handle(_connection, superhero);
      return null;
    });
  }

  @Override
  public List<Superhero> getAll() {
    final String _sql = "SELECT * FROM superheroes";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfSuperpower = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "superpower");
        final int _columnIndexOfStrengthLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strengthLevel");
        final int _columnIndexOfIsVillain = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVillain");
        final List<Superhero> _result = new ArrayList<Superhero>();
        while (_stmt.step()) {
          final Superhero _item;
          _item = new Superhero();
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          _item.setId(_tmpId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item.setName(_tmpName);
          final String _tmpSuperpower;
          if (_stmt.isNull(_columnIndexOfSuperpower)) {
            _tmpSuperpower = null;
          } else {
            _tmpSuperpower = _stmt.getText(_columnIndexOfSuperpower);
          }
          _item.setSuperpower(_tmpSuperpower);
          final int _tmpStrengthLevel;
          _tmpStrengthLevel = (int) (_stmt.getLong(_columnIndexOfStrengthLevel));
          _item.setStrengthLevel(_tmpStrengthLevel);
          final boolean _tmpIsVillain;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVillain));
          _tmpIsVillain = _tmp != 0;
          _item.setVillain(_tmpIsVillain);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Superhero getById(final long id) {
    final String _sql = "SELECT * FROM superheroes WHERE id = ?";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfSuperpower = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "superpower");
        final int _columnIndexOfStrengthLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strengthLevel");
        final int _columnIndexOfIsVillain = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVillain");
        final Superhero _result;
        if (_stmt.step()) {
          _result = new Superhero();
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          _result.setId(_tmpId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _result.setName(_tmpName);
          final String _tmpSuperpower;
          if (_stmt.isNull(_columnIndexOfSuperpower)) {
            _tmpSuperpower = null;
          } else {
            _tmpSuperpower = _stmt.getText(_columnIndexOfSuperpower);
          }
          _result.setSuperpower(_tmpSuperpower);
          final int _tmpStrengthLevel;
          _tmpStrengthLevel = (int) (_stmt.getLong(_columnIndexOfStrengthLevel));
          _result.setStrengthLevel(_tmpStrengthLevel);
          final boolean _tmpIsVillain;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVillain));
          _tmpIsVillain = _tmp != 0;
          _result.setVillain(_tmpIsVillain);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public List<Superhero> getByMinStrength(final int minStrength) {
    final String _sql = "SELECT * FROM superheroes WHERE strengthLevel > ?";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, minStrength);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfSuperpower = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "superpower");
        final int _columnIndexOfStrengthLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strengthLevel");
        final int _columnIndexOfIsVillain = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVillain");
        final List<Superhero> _result = new ArrayList<Superhero>();
        while (_stmt.step()) {
          final Superhero _item;
          _item = new Superhero();
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          _item.setId(_tmpId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item.setName(_tmpName);
          final String _tmpSuperpower;
          if (_stmt.isNull(_columnIndexOfSuperpower)) {
            _tmpSuperpower = null;
          } else {
            _tmpSuperpower = _stmt.getText(_columnIndexOfSuperpower);
          }
          _item.setSuperpower(_tmpSuperpower);
          final int _tmpStrengthLevel;
          _tmpStrengthLevel = (int) (_stmt.getLong(_columnIndexOfStrengthLevel));
          _item.setStrengthLevel(_tmpStrengthLevel);
          final boolean _tmpIsVillain;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVillain));
          _tmpIsVillain = _tmp != 0;
          _item.setVillain(_tmpIsVillain);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public List<Superhero> getOnlyHeroes() {
    final String _sql = "SELECT * FROM superheroes WHERE isVillain = 0";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfSuperpower = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "superpower");
        final int _columnIndexOfStrengthLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strengthLevel");
        final int _columnIndexOfIsVillain = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVillain");
        final List<Superhero> _result = new ArrayList<Superhero>();
        while (_stmt.step()) {
          final Superhero _item;
          _item = new Superhero();
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          _item.setId(_tmpId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item.setName(_tmpName);
          final String _tmpSuperpower;
          if (_stmt.isNull(_columnIndexOfSuperpower)) {
            _tmpSuperpower = null;
          } else {
            _tmpSuperpower = _stmt.getText(_columnIndexOfSuperpower);
          }
          _item.setSuperpower(_tmpSuperpower);
          final int _tmpStrengthLevel;
          _tmpStrengthLevel = (int) (_stmt.getLong(_columnIndexOfStrengthLevel));
          _item.setStrengthLevel(_tmpStrengthLevel);
          final boolean _tmpIsVillain;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVillain));
          _tmpIsVillain = _tmp != 0;
          _item.setVillain(_tmpIsVillain);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public List<Superhero> getOnlyVillains() {
    final String _sql = "SELECT * FROM superheroes WHERE isVillain = 1";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfSuperpower = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "superpower");
        final int _columnIndexOfStrengthLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strengthLevel");
        final int _columnIndexOfIsVillain = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVillain");
        final List<Superhero> _result = new ArrayList<Superhero>();
        while (_stmt.step()) {
          final Superhero _item;
          _item = new Superhero();
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          _item.setId(_tmpId);
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          _item.setName(_tmpName);
          final String _tmpSuperpower;
          if (_stmt.isNull(_columnIndexOfSuperpower)) {
            _tmpSuperpower = null;
          } else {
            _tmpSuperpower = _stmt.getText(_columnIndexOfSuperpower);
          }
          _item.setSuperpower(_tmpSuperpower);
          final int _tmpStrengthLevel;
          _tmpStrengthLevel = (int) (_stmt.getLong(_columnIndexOfStrengthLevel));
          _item.setStrengthLevel(_tmpStrengthLevel);
          final boolean _tmpIsVillain;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsVillain));
          _tmpIsVillain = _tmp != 0;
          _item.setVillain(_tmpIsVillain);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public int getCount() {
    final String _sql = "SELECT COUNT(*) FROM superheroes";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _result;
        if (_stmt.step()) {
          _result = (int) (_stmt.getLong(0));
        } else {
          _result = 0;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public void deleteAll() {
    final String _sql = "DELETE FROM superheroes";
    DBUtil.performBlocking(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return null;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
