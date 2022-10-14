package shady.samir.adminetwak3kora.Models.ResponseModel.Help


import com.google.gson.annotations.SerializedName

data class AddHelpResponseModel(
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
        @SerializedName("descriptionAr")
        var descriptionAr: String? = null,
        @SerializedName("descriptionEn")
        var descriptionEn: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("titleAr")
        var titleAr: String? = null,
        @SerializedName("titleEn")
        var titleEn: String? = null
    )
}