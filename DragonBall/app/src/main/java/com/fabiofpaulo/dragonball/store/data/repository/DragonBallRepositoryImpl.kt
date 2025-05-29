package com.fabiofpaulo.dragonball.store.data.repository

import arrow.core.Either
import com.fabiofpaulo.dragonball.store.data.mapper.toGeneralError
import com.fabiofpaulo.dragonball.store.data.remote.DragonBallApi
import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacter
import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacterDetails
import com.fabiofpaulo.dragonball.store.domain.model.ListResponse
import com.fabiofpaulo.dragonball.store.domain.model.NetworkError
import com.fabiofpaulo.dragonball.store.domain.repository.DragonBallRepository
import javax.inject.Inject

class DragonBallRepositoryImpl @Inject constructor(
    private val dragonBallApi: DragonBallApi
): DragonBallRepository {
    override suspend fun getCharacters(): Either<NetworkError, ListResponse<DragonBallCharacter>> {
        return Either.Companion.catch {
            dragonBallApi.getCharacters()
        }.mapLeft { it.toGeneralError() }
    }

    override suspend fun getCharacterDetails(characterId: Int): Either<NetworkError, DragonBallCharacterDetails> {
        return Either.Companion.catch {
            dragonBallApi.getCharacterDetails(characterId)
        }.mapLeft { it.toGeneralError() }
    }
}