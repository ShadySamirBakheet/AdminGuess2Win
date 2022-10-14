package shady.samir.adminetwak3kora.Models.ResponseModel.Teams


import com.google.gson.annotations.SerializedName
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeagueResponseModel
import java.io.File

data class TeamResponseModel(
    @SerializedName("team")
    var team:Team?= null
){
    data class Team(
        @SerializedName("countryEn")
        var countryEn: String? = null,
        @SerializedName("countryAr")
        var countryAr: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("nameAR")
        var nameAR: String? = null,
        @SerializedName("nameEn")
        var nameEn: String? = null
    )

}