package com.arthur.albo.punktest.data.api

import javax.inject.Inject

class BeerRemoteDataSource @Inject constructor(private val beersService: BeersService) : BaseDataSource() {
    suspend fun getBeers(page : Int) = getResult { beersService.getBeers(page, 20) }
    suspend fun getBeer(id: Int) = getResult { beersService.getBeer(id) }
}