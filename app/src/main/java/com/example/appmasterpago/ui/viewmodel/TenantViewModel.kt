package com.example.appmasterpago.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.appmasterpago.services.service_utils.MasterpagoService_Util
import com.example.appmasterpago.utils.base_model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class TenantViewModel @ViewModelInject constructor(
    private val MasterpagoService: MasterpagoService_Util,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {



    val listaSearchTenant =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)

            try {
                MasterpagoService.getTenants().collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

}