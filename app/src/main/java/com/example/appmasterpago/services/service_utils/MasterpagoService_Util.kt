package com.example.appmasterpago.services.service_utils

import com.example.appmasterpago.models.api_data.MasterpagoResponse
import com.example.appmasterpago.services.MasterpagoServices
import com.example.appmasterpago.utils.base_model.Resource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


/**Se encarga de llamar la logica de BD o API ,
 * no importa quien sea el sirve de intermediario solament*/
class MasterpagoService_Util @Inject constructor(

) {

    //region API CALLBACKS
    private val api = MasterpagoServices()

    /**
     * Se encarga de llamar al Servicio de la Api para llevarlo al model
     * Trabaja con un callbackFlow para saber si viene con exito la información o no
     * @param query Lo escrito en la busqueda
     * @param page realizado por paginación
     * @param filtros Los tipos de filtros utilizados
     */
    suspend fun getTenants():
            Flow<Resource<MasterpagoResponse>> =
        callbackFlow {

            //offer(getCachedCocktails(cocktailName))

            api.getTenants().collect {
                when (it) {
                    is Resource.Success -> {
                        offer(it)
                    }
                    is Resource.Failure -> {
                        offer(it)
                    }
                }
            }
            awaitClose { cancel() }
        }

    //endregion
}