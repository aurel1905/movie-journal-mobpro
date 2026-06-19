package com.aureliasalma0066.mobpro1.model

data class Film(
    val id: Long? = null,
    val user_id: String,
    val title: String,
    val review: String,
    val rating: Int,
    val image_url: String? = null
)