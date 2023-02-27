package com.lindenlabs.truckrouter.android.ui.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    var data: MutableLiveData<HomeViewEntity> = MutableLiveData(HomeViewEntity(0.0, emptyList(), ""))

    init {
        viewModelScope.launch {
            kotlin.runCatching {   getScheduleDomainEntity() }
                .mapCatching { viewMapper.map(it)  }
                .onSuccess {
                    Log.d("Success", it.toString())
                    data.value = it
                }
                .onFailure { Log.d("Failure", it.toString()) }
        }
    }
}