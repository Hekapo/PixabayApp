package com.example.pixabayapp.di

import com.example.pixabayapp.data.network.SearchService
import com.example.pixabayapp.data.repository.HitsRepositoryImpl
import com.example.pixabayapp.domain.repository.HitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideHitsRepository(service: SearchService): HitsRepository {
        return HitsRepositoryImpl(service)
    }

}
