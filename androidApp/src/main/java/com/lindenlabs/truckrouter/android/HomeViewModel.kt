package com.lindenlabs.truckrouter.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.truckrouter.domain.GetScheduleDomainEntity
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ViewMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getScheduleDomainEntity: GetScheduleDomainEntity, val viewMapper: ViewMapper) : ViewModel() {

    var data: HomeViewEntity? = null
    init {
        viewModelScope.launch {
            kotlin.runCatching {   getScheduleDomainEntity() }
                .mapCatching { viewMapper.map(it)  }
                .onSuccess {
                    Log.d("Success", it.toString())
                    data = it
                }
                .onFailure { Log.d("Failure", it.toString()) }
        }
    }
}