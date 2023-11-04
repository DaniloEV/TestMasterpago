package com.example.appmasterpago.utils.base_model

/**
 * Se encarga de poder saber el estado de la informacion que viene de la API, ya que manera principal no se puede
 */
sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}