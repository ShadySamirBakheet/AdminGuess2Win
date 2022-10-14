package shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse

import com.google.gson.annotations.SerializedName

data class AddMatchRequestModel(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("Date")
    var date: String? = null,
    @SerializedName("FirstTeamId")
    var firstTeamId: Int? = null,
    @SerializedName("IsEnded")
    var isEnded: Boolean? = null,
    @SerializedName("IsStarted")
    var isStarted: Boolean? = null,
    @SerializedName("LeagueId")
    var leagueId: Int? = null,
    @SerializedName("NoOfFirstTeamGoals")
    var noOfFirstTeamGoals: Int? = null,
    @SerializedName("NoOfSecondTeamGoals")
    var noOfSecondTeamGoals: Int? = null,
    @SerializedName("SecondTeamId")
    var secondTeamId: Int? = null,
    @SerializedName("Time")
    var time: String? = null
)