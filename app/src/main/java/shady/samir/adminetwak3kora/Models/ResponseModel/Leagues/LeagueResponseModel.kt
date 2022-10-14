package shady.samir.adminetwak3kora.Models.ResponseModel.Leagues


import com.google.gson.annotations.SerializedName

data class LeagueResponseModel(
    @SerializedName("league")
    var league: League? = null
) {
    data class League(
        @SerializedName("endDate")
        var endDate: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("startDate")
        var startDate: String? = null
    )
}