package com.fabiofpaulo.dragonball.store.domain.repository

import arrow.core.Either
import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacter
import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacterDetails
import com.fabiofpaulo.dragonball.store.domain.model.ListResponse
import com.fabiofpaulo.dragonball.store.domain.model.NetworkError


interface DragonBallRepository {
    suspend fun getCharacters(): Either<NetworkError, ListResponse<DragonBallCharacter>>

    suspend fun getCharacterDetails(characterId: Int): Either<NetworkError, DragonBallCharacterDetails>
}