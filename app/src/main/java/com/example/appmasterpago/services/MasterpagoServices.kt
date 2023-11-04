package com.example.appmasterpago.services

import com.example.appmasterpago.core.RetrofitHelper
import com.example.appmasterpago.models.api_data.MasterpagoResponse
import com.example.appmasterpago.utils.base_model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Clase que sirve de conexión intermedia entre el ApiClient y el repository
 * Se encarga de concatenar lo que se ocupe, además de retornar al repository
 */
@ExperimentalCoroutinesApi
class MasterpagoServices {
    private val retrofit= RetrofitHelper.baseUrlMasterpago()


    /**
     * Se encarga de llamar al ApiClient para colocar lo que se pida en el buscador y con el filtro
     *
     * @param query Lo escrito en la busqueda
     * @param page realizado por paginación
     * @param filtros Los tipos de filtros utilizados
     */
    suspend fun getTenants(): Flow<Resource<MasterpagoResponse>> =
        callbackFlow {
            offer(
                Resource.Success(
                    retrofit.create(IMasterpagoServices::class.java)
                        .getTenants("services/app/TenantAppServicePublic/GetTenants")
                )
            )
            awaitClose { close() }
        }



}