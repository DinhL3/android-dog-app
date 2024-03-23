package com.example.finalproject

data class Breed(
    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: Relationships
)

data class Attributes(
    val name: String,
    val description: String,
    val life: Life,
    val male_weight: Weight,
    val female_weight: Weight,
    val hypoallergenic: Boolean
)

data class Life(
    val max: Byte,
    val min: Byte
)

data class Weight(
    val max: Short,
    val min: Short
)

data class Relationships(
    val group: Group
)

data class Group(
    val data: GroupData
)

data class GroupData(
    val id: String,
    val type: String
)

data class BreedsResponse(
    val data: List<Breed>,
    val links: Links
)

data class BreedByIdResponse(
    val data: Breed,
    val links: Links
)


data class Links(
    val self: String,
    val current: String,
    val next: String,
    val last: String
)
