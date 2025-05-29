package com.fabiofpaulo.dragonball.di

import com.fabiofpaulo.dragonball.store.data.remote.DragonBallApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDragonBallApi(): DragonBallApi {
        return Retrofit
            .Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DragonBallApi::class.java)
    }
}