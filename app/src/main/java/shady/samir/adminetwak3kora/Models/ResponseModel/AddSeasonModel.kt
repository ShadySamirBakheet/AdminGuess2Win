package shady.samir.adminetwak3kora.Models.ResponseModel

import com.google.gson.annotations.SerializedName

data class AddSeasonModel(
    @SerializedName("EndSeason")
    val EndSeason: String,
    @SerializedName("NameAr")
    val NameAr: String,
    @SerializedName("NameEn")
    val NameEn: String,
    @SerializedName("StartSeason")
    val StartSeason: String
)