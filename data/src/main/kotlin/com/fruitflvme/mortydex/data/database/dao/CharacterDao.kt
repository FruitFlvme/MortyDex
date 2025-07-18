package com.fruitflvme.mortydex.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fruitflvme.mortydex.data.database.entity.CharacterEntity
import com.fruitflvme.mortydex.domain.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity

    @Query(
        """
        SELECT * FROM characters
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
          AND (:status IS NULL OR status = :status)
          AND (:species IS NULL OR species = :species)
          AND (:gender IS NULL OR gender = :gender)
        ORDER BY id ASC
    """
    )
    fun getCharactersFiltered(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ): PagingSource<Int, Character>

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}