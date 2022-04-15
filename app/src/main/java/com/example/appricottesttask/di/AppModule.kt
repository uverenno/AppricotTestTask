package com.example.appricottesttask.di

import com.example.data.remote.CharactersService
import com.example.data.remote.RickAndMortyPagingSource
import com.example.data.repository.details.DetailsRepositoryImpl
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule :: class, BindModule :: class])
class AppModule {

    @Provides
    @AppScope
    fun providePageSource(
        service: CharactersService
    ) : RickAndMortyPagingSource = RickAndMortyPagingSource(service)


    @Provides
    @AppScope
    fun provideDetailsRepository(
        service: CharactersService
    ) : DetailsRepositoryImpl = DetailsRepositoryImpl(service)
}