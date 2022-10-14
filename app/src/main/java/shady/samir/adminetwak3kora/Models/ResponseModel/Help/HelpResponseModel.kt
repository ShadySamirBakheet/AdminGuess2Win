package shady.samir.adminetwak3kora.Models.ResponseModel.Help


import com.google.gson.annotations.SerializedName

data class HelpResponseModel(
    @SerializedName("data")
    var `data`: List<Data>? = null
) {
    data class Data(
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("title")
        var title: String? = null
    )
}