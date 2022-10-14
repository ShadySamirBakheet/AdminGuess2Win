package shady.samir.adminetwak3kora.Models.ResponseModel


import com.google.gson.annotations.SerializedName

data class AddMonthModel(
    @SerializedName("StartMonth")
    var startMonth: String? = null,
    @SerializedName("EndMonth")
    var endMonth: String? = null,
    @SerializedName("NameAr")
    var NameAr: String? = null,
    @SerializedName("NameEn")
    var NameEn: String? = null
)