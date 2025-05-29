package com.fabiofpaulo.dragonball.di

import com.fabiofpaulo.dragonball.store.data.repository.DragonBallRepositoryImpl
import com.fabiofpaulo.dragonball.store.domain.repository.DragonBallRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideDragonBallRepository(impl: DragonBallRepositoryImpl): DragonBallRepository
}