package shady.samir.adminetwak3kora.Models.ResponseModel


import com.google.gson.annotations.SerializedName

data class AddWeekModel(
    @SerializedName("StartWeek")
    var startWeek: String? = null,
    @SerializedName("EndWeek")
    var endWeek: String? = null,
    @SerializedName("NameAr")
    var NameAr: String? = null,
    @SerializedName("NameEn")
    var NameEn: String? = null
)