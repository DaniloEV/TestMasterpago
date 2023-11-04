package com.example.appmasterpago.services

import com.example.appmasterpago.models.api_data.MasterpagoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
Está interfaz es la que se encarga de crear y podemos acceder al servicio que realiza las consultas a la API, con la URL esperada
Va a cambiar dependiendo de lo que se ocupe a traer
 */
interface IMasterpagoServices {
    /**
     * Interfaz que trae la info de los tenants
     */
    @GET
    suspend fun getTenants(@Url url: String):MasterpagoResponse

}