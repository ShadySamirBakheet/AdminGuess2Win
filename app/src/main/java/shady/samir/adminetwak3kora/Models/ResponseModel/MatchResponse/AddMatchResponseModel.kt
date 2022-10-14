package shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse


import com.google.gson.annotations.SerializedName

data class AddMatchResponseModel(
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
        @SerializedName("date")
        var date: String? = null,
        @SerializedName("firstTeam")
        var firstTeam: Any? = null,
        @SerializedName("firstTeamID")
        var firstTeamID: Int? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("isEnded")
        var isEnded: Boolean? = null,
        @SerializedName("isStarted")
        var isStarted: Boolean? = null,
        @SerializedName("league")
        var league: Any? = null,
        @SerializedName("leagueID")
        var leagueID: Int? = null,
        @SerializedName("noOfFirstTeamGoals")
        var noOfFirstTeamGoals: Any? = null,
        @SerializedName("noOfSecondTeamGoals")
        var noOfSecondTeamGoals: Any? = null,
        @SerializedName("secondTeam")
        var secondTeam: Any? = null,
        @SerializedName("secondTeamID")
        var secondTeamID: Int? = null,
        @SerializedName("time")
        var time: String? = null
    )
}