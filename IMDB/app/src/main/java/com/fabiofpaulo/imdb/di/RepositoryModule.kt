package com.fabiofpaulo.imdb.di

import com.fabiofpaulo.imdb.store.data.repository.TmdbRepositoryImpl
import com.fabiofpaulo.imdb.store.domain.repository.TmdbRepository
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
    abstract fun provideTmdbRepository(impl: TmdbRepositoryImpl): TmdbRepository
}

