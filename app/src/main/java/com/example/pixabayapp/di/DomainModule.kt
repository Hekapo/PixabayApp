package com.example.pixabayapp.di

import com.example.pixabayapp.domain.repository.HitsRepository
import com.example.pixabayapp.domain.usecase.SearchImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSearchImageUseCase(repository: HitsRepository): SearchImageUseCase {
        return SearchImageUseCase(repository)
    }
}
