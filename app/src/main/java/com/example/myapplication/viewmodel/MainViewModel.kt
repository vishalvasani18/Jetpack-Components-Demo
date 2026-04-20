package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.DataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val toggleState: StateFlow<Boolean> =
        dataStoreManager.toggleState
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            )

    fun onToggleChanged(isOn: Boolean) {
        viewModelScope.launch {
            dataStoreManager.saveToggleState(isOn)
        }
    }
}