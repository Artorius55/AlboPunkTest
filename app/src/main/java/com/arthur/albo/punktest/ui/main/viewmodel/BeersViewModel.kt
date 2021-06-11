package com.arthur.albo.punktest.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.arthur.albo.punktest.data.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {
    var page = 1

    val beers = repository.getBeers(page)
}

