package com.fruitflvme.mortydex.`data`.database.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.fruitflvme.mortydex.`data`.database.entity.RemoteKey
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RemoteKeyDao_Impl(
  __db: RoomDatabase,
) : RemoteKeyDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRemoteKey: EntityInsertAdapter<RemoteKey>
  init {
    this.__db = __db
    this.__insertAdapterOfRemoteKey = object : EntityInsertAdapter<RemoteKey>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `remote_keys` (`id`,`nextPage`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteKey) {
        statement.bindText(1, entity.id)
        val _tmpNextPage: Int? = entity.nextPage
        if (_tmpNextPage == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpNextPage.toLong())
        }
      }
    }
  }

  public override suspend fun insertRemoteKey(key: RemoteKey): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfRemoteKey.insert(_connection, key)
  }

  public override suspend fun insertAll(keys: List<RemoteKey>): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfRemoteKey.insert(_connection, keys)
  }

  public override suspend fun getRemoteKeyById(id: String): RemoteKey? {
    val _sql: String = "SELECT * FROM remote_keys WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfNextPage: Int = getColumnIndexOrThrow(_stmt, "nextPage")
        val _result: RemoteKey?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpNextPage: Int?
          if (_stmt.isNull(_columnIndexOfNextPage)) {
            _tmpNextPage = null
          } else {
            _tmpNextPage = _stmt.getLong(_columnIndexOfNextPage).toInt()
          }
          _result = RemoteKey(_tmpId,_tmpNextPage)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAll() {
    val _sql: String = "DELETE FROM remote_keys"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteRemoteKey(id: String) {
    val _sql: String = "DELETE FROM remote_keys WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
