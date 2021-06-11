package com.arthur.albo.punktest.di

import android.content.Context
import com.arthur.albo.punktest.data.api.BeerRemoteDataSource
import com.arthur.albo.punktest.data.api.BeersService
import com.arthur.albo.punktest.data.local.AppDatabase
import com.arthur.albo.punktest.data.local.BeerDao
import com.arthur.albo.punktest.data.repository.BeerRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.punkapi.com/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideBeersService(retrofit: Retrofit): BeersService = retrofit.create(BeersService::class.java)

    @Singleton
    @Provides
    fun provideBeersRemoteDataSource(beersService: BeersService) = BeerRemoteDataSource(beersService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBeerDao(db: AppDatabase) = db.beerDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: BeerRemoteDataSource, localDataSource: BeerDao) = BeerRepository(remoteDataSource, localDataSource)
}