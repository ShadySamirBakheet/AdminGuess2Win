package shady.samir.adminetwak3kora.Models.ResponseModel


import com.google.gson.annotations.SerializedName

data class AwardsResponseModel(
    @SerializedName("data")
    var `data`: List<Data>? = null
) {
    data class Data(
        @SerializedName("fromPoint")
        var fromPoint: Int? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("toPoint")
        var toPoint: Int? = null,
        @SerializedName("type")
        var type: Int? = null
    )
}