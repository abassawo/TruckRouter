package com.lindenlabs.truckrouter.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.truckrouter.domain.GetScheduleDomainEntity
import com.lindenlabs.truckrouter.domain.ScheduleDomainEntity
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getScheduleDomainEntity: GetScheduleDomainEntity) : ViewModel() {

    var data: ScheduleDomainEntity = mutableSetOf()
    init {
        viewModelScope.launch {
            kotlin.runCatching {   getScheduleDomainEntity() }
                .onSuccess {
                    Log.d("Success", it.toString())
                    data = it
                }
                .onFailure { Log.d("Failure", it.toString()) }
        }

    }
}