package com.arekalov.data.api

sealed class Status {
    data class OK<T>(val data: T) : Status()
    data class ERROR(val error: String) : Status()

}