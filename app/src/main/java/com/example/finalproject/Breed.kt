package com.example.finalproject

data class Breed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val bred_for: String,
    val breed_group: String?,
    val life_span: String,
    val temperament: String,
    val origin: String,
    val reference_image_id: String,
    val image: Image
)

data class Weight(
    val imperial: String,
    val metric: String
)

data class Height(
    val imperial: String,
    val metric: String
)

data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
)

