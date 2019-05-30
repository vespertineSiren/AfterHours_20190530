package dev.vespertine.afterhoursmay28


data class QueenDetailsModels(
    val challenges: List<Challenge>,
    val episodes: List<Episode>,
    val id: Int,
    val image_url: String,
    val lipsyncs: List<Lipsync>,
    val missCongeniality: Boolean,
    val name: String,
    val quote: String,
    val seasons: List<Season>,
    val winner: Boolean
)

data class Season(
    val id: Int,
    val place: Int,
    val seasonNumber: String
)

data class Challenge(
    val description: String,
    val id: Int,
    val type: String,
    val won: Boolean
)

data class Episode(
    val airDate: String,
    val episodeInSeason: Int,
    val id: Int,
    val seasonId: Int,
    val title: String
)

data class Lipsync(
    val artist: String,
    val id: Int,
    val name: String,
    val won: Boolean
)