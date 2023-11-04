package com.example.appmasterpago.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Objecto que sirve de llamada general de retrofit, dentro de estas se puede llamar varias API's, para ello es un Helper, este posee la URL basica con que se va a
 * trabajar para la API
 */
object RetrofitHelper {
    /**
     * Función que se encarga de la llamada de la API de Masterpago
     * Está es la configuacion de retro fit es mejor así para ordenar y mantener todo en su lugar
     * @return el constructor, con el convertidor para el JSON
     * @author Documentación de la API: https://apiqa.masterpago.com
     */

    fun baseUrlMasterpago(): Retrofit {
        //GsonConverterFactory libreria de retrofit para convertir el JSON
        return Retrofit.Builder().baseUrl("https://apiqa.masterpago.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}