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
class HomeViewModel @Inject constructor(val getScheduleDomainEntity: GetScheduleDomainEntity, private val viewMapper: ViewMapper) : ViewModel() {

    var data: MutableLiveData<HomeViewEntity> = MutableLiveData(HomeViewEntity.Content(0.0, emptyList(), "", 0, maxCardWidth = 1f))

    init {
        viewModelScope.launch {
            kotlin.runCatching { getScheduleDomainEntity() }
                .mapCatching { viewMapper.map(it)  }
                .onSuccess {
                    Log.d("Success", it.toString())
                    data.value = it
                }
                .onFailure { Log.d("Failure", it.toString()) }
        }
    }

    fun onIndexChanged(index: Int) {
        val newValue = (data.value as? HomeViewEntity.Content)?.copy(selectedIndex = index)
        newValue?.let {
            data.value = it
        }
    }
}