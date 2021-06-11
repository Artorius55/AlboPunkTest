package com.arthur.albo.punktest.data.repository

import com.arthur.albo.punktest.data.api.BeerRemoteDataSource
import com.arthur.albo.punktest.data.local.BeerDao
import com.arthur.albo.punktest.utils.performGetOperation
import javax.inject.Inject


class BeerRepository @Inject constructor(private val remoteDataSource: BeerRemoteDataSource, private val localDataSource: BeerDao) {
    fun getBeers(page : Int) = performGetOperation(
        databaseQuery = { localDataSource.getAllBeers() },
        networkCall = { remoteDataSource.getBeers(page) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getBeer(id) },
        networkCall = { remoteDataSource.getBeer(id) },
        saveCallResult = { localDataSource.insert(it) }
    )
}