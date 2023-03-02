package com.lindenlabs.desktop

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.lindenlabs.truckrouter.JvmPlatform
import com.lindenlabs.truckrouter.domain.GetScheduleDomainEntity
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ViewMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel {

    var results: MutableState<HomeViewEntity> = mutableStateOf(HomeViewEntity.Loading)
    private val getScheduleDomainEntity: GetScheduleDomainEntity =
        GetScheduleDomainEntity(JvmPlatform(), ScheduleDomainMapper())
    private val viewMapper = ViewMapper()
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    init {
        ioScope.launch {
            kotlin.runCatching { getScheduleDomainEntity() }
                .mapCatching { viewMapper.map(it)  }
                .onSuccess { results.value = it  }
        }
    }

    fun onIndexChanged(index: Int) {
        (results.value as? HomeViewEntity.Content)?.let { content ->
            results.value = content.copy(selectedIndex = index)
        }
    }
}