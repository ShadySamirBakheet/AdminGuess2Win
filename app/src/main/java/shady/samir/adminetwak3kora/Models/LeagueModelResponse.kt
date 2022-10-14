package shady.samir.adminetwak3kora.Models


import com.google.gson.annotations.SerializedName

data class LeagueModelResponse(
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
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
}