package com.example.appricottesttask.di

import com.example.data.remote.CharactersService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object NetworkModule {

    @Provides
    @AppScope
    fun provideRetrofit(@BaseUrlQualifier baseUrl: String): Retrofit =
        Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()


    @Provides
    @AppScope
    fun provideNewsService(retrofit: Retrofit): CharactersService =
        retrofit.create(CharactersService::class.java)

}