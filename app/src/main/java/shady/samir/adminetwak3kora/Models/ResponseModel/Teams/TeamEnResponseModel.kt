package shady.samir.adminetwak3kora.Models.ResponseModel.Teams


import com.google.gson.annotations.SerializedName

data class TeamEnResponseModel(
    @SerializedName("team")
    var team: Team? = null
) {
    data class Team(
        @SerializedName("country")
        var country: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("name")
        var name: String? = null
    )
}