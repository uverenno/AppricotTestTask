package com.example.appricottesttask.di

import com.example.data.repository.details.DetailsRepository
import com.example.data.repository.details.DetailsRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface BindModule {

    @Binds
    @AppScope
    fun bindDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl) : DetailsRepository
}