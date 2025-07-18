package com.fruitflvme.mortydex.`data`.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.fruitflvme.mortydex.`data`.database.dao.CharacterDao
import com.fruitflvme.mortydex.`data`.database.dao.CharacterDao_Impl
import com.fruitflvme.mortydex.`data`.database.dao.RemoteKeyDao
import com.fruitflvme.mortydex.`data`.database.dao.RemoteKeyDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _characterDao: Lazy<CharacterDao> = lazy {
    CharacterDao_Impl(this)
  }

  private val _remoteKeyDao: Lazy<RemoteKeyDao> = lazy {
    RemoteKeyDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "56cadacf16afe1dee5a9b71271a4d28c", "9d485d67eb21977b37f4fe59a7ca7a8c") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `characters` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `status` TEXT NOT NULL, `species` TEXT NOT NULL, `type` TEXT NOT NULL, `gender` TEXT NOT NULL, `origin` TEXT NOT NULL, `location` TEXT NOT NULL, `image` TEXT NOT NULL, `episodeCount` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `remote_keys` (`id` TEXT NOT NULL, `nextPage` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '56cadacf16afe1dee5a9b71271a4d28c')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `characters`")
        connection.execSQL("DROP TABLE IF EXISTS `remote_keys`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsCharacters: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCharacters.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("species", TableInfo.Column("species", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("gender", TableInfo.Column("gender", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("origin", TableInfo.Column("origin", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("location", TableInfo.Column("location", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("image", TableInfo.Column("image", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCharacters.put("episodeCount", TableInfo.Column("episodeCount", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCharacters: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCharacters: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCharacters: TableInfo = TableInfo("characters", _columnsCharacters,
            _foreignKeysCharacters, _indicesCharacters)
        val _existingCharacters: TableInfo = read(connection, "characters")
        if (!_infoCharacters.equals(_existingCharacters)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |characters(com.fruitflvme.mortydex.data.database.entity.CharacterEntity).
              | Expected:
              |""".trimMargin() + _infoCharacters + """
              |
              | Found:
              |""".trimMargin() + _existingCharacters)
        }
        val _columnsRemoteKeys: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRemoteKeys.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRemoteKeys.put("nextPage", TableInfo.Column("nextPage", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRemoteKeys: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRemoteKeys: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRemoteKeys: TableInfo = TableInfo("remote_keys", _columnsRemoteKeys,
            _foreignKeysRemoteKeys, _indicesRemoteKeys)
        val _existingRemoteKeys: TableInfo = read(connection, "remote_keys")
        if (!_infoRemoteKeys.equals(_existingRemoteKeys)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |remote_keys(com.fruitflvme.mortydex.data.database.entity.RemoteKey).
              | Expected:
              |""".trimMargin() + _infoRemoteKeys + """
              |
              | Found:
              |""".trimMargin() + _existingRemoteKeys)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "characters", "remote_keys")
  }

  public override fun clearAllTables() {
    super.performClear(false, "characters", "remote_keys")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(CharacterDao::class, CharacterDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RemoteKeyDao::class, RemoteKeyDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun characterDao(): CharacterDao = _characterDao.value

  public override fun remoteKeyDao(): RemoteKeyDao = _remoteKeyDao.value
}
