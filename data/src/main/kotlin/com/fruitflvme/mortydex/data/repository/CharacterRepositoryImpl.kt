@file:OptIn(ExperimentalPagingApi::class)

package com.fruitflvme.mortydex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fruitflvme.mortydex.data.database.AppDatabase
import com.fruitflvme.mortydex.data.mapper.toDomain
import com.fruitflvme.mortydex.data.mapper.toEntity
import com.fruitflvme.mortydex.data.mediator.CharacterRemoteMediator
import com.fruitflvme.mortydex.data.network.RickAndMortyApi
import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import com.fruitflvme.mortydex.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val db: AppDatabase
) : ICharacterRepository {
    override fun getCharacters(query: CharacterQuery): Flow<PagingData<Character>> {
        val pagingSourceFactory = {
            db.characterDao()
                .getCharactersFiltered(query.name, query.status, query.species, query.gender)
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                enablePlaceholders = true
            ),
            remoteMediator = CharacterRemoteMediator(api, db, query),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getCharacterById(id: Int): Character {
        return try {
            val remote = api.getCharacterById(id)
            db.characterDao().insert(remote.toEntity())
            remote.toDomain()
        } catch (e: Exception) {
            db.characterDao().getCharacterById(id).toDomain()
        }
    }
}