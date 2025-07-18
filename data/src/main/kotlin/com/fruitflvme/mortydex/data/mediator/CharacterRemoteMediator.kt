package com.fruitflvme.mortydex.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fruitflvme.mortydex.data.database.AppDatabase
import com.fruitflvme.mortydex.data.database.entity.RemoteKey
import com.fruitflvme.mortydex.data.mapper.toEntity
import com.fruitflvme.mortydex.data.network.RickAndMortyApi
import com.fruitflvme.mortydex.domain.model.Character
import com.fruitflvme.mortydex.domain.model.CharacterQuery
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharacterRemoteMediator(
    private val api: RickAndMortyApi,
    private val db: AppDatabase,
    private val query: CharacterQuery
) : RemoteMediator<Int, Character>() {

    private val characterDao = db.characterDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteKeyDao.getRemoteKeyById("characters")
                    remoteKey?.nextPage ?: return MediatorResult.Success(true)
                }
            }

            val response = api.getCharacters(
                page = page,
                name = query.name,
                status = query.status,
                species = query.species,
                gender = query.gender
            )

            val characters = response.results
            val endOfPaginationReached = response.info.next == null

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                    remoteKeyDao.deleteRemoteKey("characters")
                }

                characterDao.insertAll(characters.map { it.toEntity() })

                remoteKeyDao.insertRemoteKey(
                    RemoteKey(
                        id = "characters",
                        nextPage = page + 1
                    )
                )
            }

            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}