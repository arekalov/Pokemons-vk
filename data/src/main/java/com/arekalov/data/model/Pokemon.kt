package com.arekalov.data.model

import java.io.Serializable

data class Pokemon(
    val name: String,
    val id: Int,
    val base_experience: Int,
    val is_default: Boolean,
    val height: Int,
    val weight: Int,
    val order: Int,
    val sprites: Sprites,
) : Serializable