package shady.samir.adminetwak3kora.Models.DataModel

import com.google.gson.annotations.SerializedName

data class Week(
    @SerializedName("id")
    var  weekID: Int,
    @SerializedName("nameAr")
    var  weekNameAr: String,
    @SerializedName("nameEn")
    var  weekNameEn: String,
    @SerializedName("startWeek")
    var weekStart: String,
    @SerializedName("endWeek")
    var weekEnd: String
)