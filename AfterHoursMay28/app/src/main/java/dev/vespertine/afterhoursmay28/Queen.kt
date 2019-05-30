package dev.vespertine.afterhoursmay28

import kotlinx.serialization.Serializable


@Serializable
data class Queen (
    val id: Int? = -1,
    val name: String? = "",
    val winner: Boolean? = false,
    val missCongeniality: Boolean? = false,
    val image_url: String? = "",
    val quote: String? = ""
)
