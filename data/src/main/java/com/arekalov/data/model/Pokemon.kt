package com.arekalov.data.model

data class Pokemon(
    val name: String,
    val id: Int,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val is_default: Boolean,
    val order: Int,
    val sprites: Sprites,
)