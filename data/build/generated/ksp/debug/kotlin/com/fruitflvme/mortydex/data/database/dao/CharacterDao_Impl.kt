package com.fruitflvme.mortydex.`data`.database.dao

import androidx.paging.PagingSource
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.fruitflvme.mortydex.`data`.database.entity.CharacterEntity
import com.fruitflvme.mortydex.domain.model.Character
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CharacterDao_Impl(
  __db: RoomDatabase,
) : CharacterDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCharacterEntity: EntityInsertAdapter<CharacterEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCharacterEntity = object : EntityInsertAdapter<CharacterEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `characters` (`id`,`name`,`status`,`species`,`type`,`gender`,`origin`,`location`,`image`,`episodeCount`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CharacterEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.status)
        statement.bindText(4, entity.species)
        statement.bindText(5, entity.type)
        statement.bindText(6, entity.gender)
        statement.bindText(7, entity.origin)
        statement.bindText(8, entity.location)
        statement.bindText(9, entity.image)
        statement.bindLong(10, entity.episodeCount.toLong())
      }
    }
  }

  public override suspend fun insert(character: CharacterEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfCharacterEntity.insert(_connection, character)
  }

  public override suspend fun insertAll(characters: List<CharacterEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCharacterEntity.insert(_connection, characters)
  }

  public override suspend fun getCharacterById(id: Int): CharacterEntity {
    val _sql: String = "SELECT * FROM characters WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSpecies: Int = getColumnIndexOrThrow(_stmt, "species")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
        val _columnIndexOfOrigin: Int = getColumnIndexOrThrow(_stmt, "origin")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfImage: Int = getColumnIndexOrThrow(_stmt, "image")
        val _columnIndexOfEpisodeCount: Int = getColumnIndexOrThrow(_stmt, "episodeCount")
        val _result: CharacterEntity
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSpecies: String
          _tmpSpecies = _stmt.getText(_columnIndexOfSpecies)
          val _tmpType: String
          _tmpType = _stmt.getText(_columnIndexOfType)
          val _tmpGender: String
          _tmpGender = _stmt.getText(_columnIndexOfGender)
          val _tmpOrigin: String
          _tmpOrigin = _stmt.getText(_columnIndexOfOrigin)
          val _tmpLocation: String
          _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          val _tmpImage: String
          _tmpImage = _stmt.getText(_columnIndexOfImage)
          val _tmpEpisodeCount: Int
          _tmpEpisodeCount = _stmt.getLong(_columnIndexOfEpisodeCount).toInt()
          _result =
              CharacterEntity(_tmpId,_tmpName,_tmpStatus,_tmpSpecies,_tmpType,_tmpGender,_tmpOrigin,_tmpLocation,_tmpImage,_tmpEpisodeCount)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type <com.fruitflvme.mortydex.`data`.database.entity.CharacterEntity>.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCharactersFiltered(
    name: String?,
    status: String?,
    species: String?,
    gender: String?,
  ): PagingSource<Int, Character> {
    val _sql: String = """
        |
        |        SELECT * FROM characters
        |        WHERE (? IS NULL OR name LIKE '%' || ? || '%')
        |          AND (? IS NULL OR status = ?)
        |          AND (? IS NULL OR species = ?)
        |          AND (? IS NULL OR gender = ?)
        |        ORDER BY id ASC
        |    
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      if (name == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, name)
      }
      _argIndex = 2
      if (name == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, name)
      }
      _argIndex = 3
      if (status == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, status)
      }
      _argIndex = 4
      if (status == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, status)
      }
      _argIndex = 5
      if (species == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, species)
      }
      _argIndex = 6
      if (species == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, species)
      }
      _argIndex = 7
      if (gender == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, gender)
      }
      _argIndex = 8
      if (gender == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindText(_argIndex, gender)
      }
    }
    return object : LimitOffsetPagingSource<Character>(_rawQuery, __db, "characters") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int):
          List<Character> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfSpecies: Int = getColumnIndexOrThrow(_stmt, "species")
          val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
          val _columnIndexOfGender: Int = getColumnIndexOrThrow(_stmt, "gender")
          val _columnIndexOfOrigin: Int = getColumnIndexOrThrow(_stmt, "origin")
          val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
          val _columnIndexOfImage: Int = getColumnIndexOrThrow(_stmt, "image")
          val _columnIndexOfEpisodeCount: Int = getColumnIndexOrThrow(_stmt, "episodeCount")
          val _result: MutableList<Character> = mutableListOf()
          while (_stmt.step()) {
            val _item: Character
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpStatus: String
            _tmpStatus = _stmt.getText(_columnIndexOfStatus)
            val _tmpSpecies: String
            _tmpSpecies = _stmt.getText(_columnIndexOfSpecies)
            val _tmpType: String
            _tmpType = _stmt.getText(_columnIndexOfType)
            val _tmpGender: String
            _tmpGender = _stmt.getText(_columnIndexOfGender)
            val _tmpOrigin: String
            _tmpOrigin = _stmt.getText(_columnIndexOfOrigin)
            val _tmpLocation: String
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
            val _tmpImage: String
            _tmpImage = _stmt.getText(_columnIndexOfImage)
            val _tmpEpisodeCount: Int
            _tmpEpisodeCount = _stmt.getLong(_columnIndexOfEpisodeCount).toInt()
            _item =
                Character(_tmpId,_tmpName,_tmpStatus,_tmpSpecies,_tmpType,_tmpGender,_tmpOrigin,_tmpLocation,_tmpImage,_tmpEpisodeCount)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun clearAll() {
    val _sql: String = "DELETE FROM characters"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
