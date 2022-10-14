package shady.samir.adminetwak3kora.Models.ResponseModel.Matchs


import com.google.gson.annotations.SerializedName

data class MatchsResponseModel(
    @SerializedName("data")
    var data: List<Data?>? = null
) {
    data class Data(
        @SerializedName("date")
        var date: String? = null,
        @SerializedName("firstTeam")
        var firstTeam: FirstTeam? = null,
        @SerializedName("firstTeamID")
        var firstTeamID: Int? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("isEnded")
        var isEnded: Boolean? = null,
        @SerializedName("isStarted")
        var isStarted: Boolean? = null,
        @SerializedName("league")
        var league: League? = null,
        @SerializedName("leagueID")
        var leagueID: Int? = null,
        @SerializedName("noOfFirstTeamGoals")
        var noOfFirstTeamGoals: Int? = null,
        @SerializedName("noOfSecondTeamGoals")
        var noOfSecondTeamGoals: Int? = null,
        @SerializedName("secondTeam")
        var secondTeam: SecondTeam? = null,
        @SerializedName("secondTeamID")
        var secondTeamID: Int? = null,
        @SerializedName("time")
        var time: String? = null
    ) {
        data class FirstTeam(
            @SerializedName("countryAr")
            var countryAr: String? = null,
            @SerializedName("countryEn")
            var countryEn: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("image")
            var image: String? = null,
            @SerializedName("leagues")
            var leagues: Any? = null,
            @SerializedName("nameAR")
            var nameAR: String? = null,
            @SerializedName("nameEn")
            var nameEn: String? = null
        )

        data class League(
            @SerializedName("endDate")
            var endDate: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("image")
            var image: String? = null,
            @SerializedName("nameAR")
            var nameAR: String? = null,
            @SerializedName("nameEn")
            var nameEn: String? = null,
            @SerializedName("startDate")
            var startDate: String? = null,
            @SerializedName("teams")
            var teams: Any? = null
        )

        data class SecondTeam(
            @SerializedName("countryAr")
            var countryAr: String? = null,
            @SerializedName("countryEn")
            var countryEn: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("image")
            var image: String? = null,
            @SerializedName("leagues")
            var leagues: Any? = null,
            @SerializedName("nameAR")
            var nameAR: String? = null,
            @SerializedName("nameEn")
            var nameEn: String? = null
        )
    }
}