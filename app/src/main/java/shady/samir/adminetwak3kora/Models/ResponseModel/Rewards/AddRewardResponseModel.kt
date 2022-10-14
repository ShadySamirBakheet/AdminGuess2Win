package shady.samir.adminetwak3kora.Models.ResponseModel.Rewards


import com.google.gson.annotations.SerializedName

data class AddRewardResponseModel(
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
        @SerializedName("fromPoint")
        var fromPoint: Int? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("nameAr")
        var nameAr: String? = null,
        @SerializedName("nameEn")
        var nameEn: String? = null,
        @SerializedName("toPoint")
        var toPoint: Int? = null,
        @SerializedName("type")
        var type: Int? = null
    )
}