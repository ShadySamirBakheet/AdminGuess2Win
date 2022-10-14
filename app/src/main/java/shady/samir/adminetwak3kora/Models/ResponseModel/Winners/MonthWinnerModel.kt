package shady.samir.adminetwak3kora.Models.ResponseModel.Winners


import com.google.gson.annotations.SerializedName

class MonthWinnerModel : ArrayList<MonthWinnerModel.MonthWinnerModelItem>(){
    data class MonthWinnerModelItem(
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("month")
        var month: Month? = null,
        @SerializedName("monthID")
        var monthID: Int? = null,
        @SerializedName("score")
        var score: Int? = null,
        @SerializedName("user")
        var user: User? = null,
        @SerializedName("userID")
        var userID: String? = null
    ) {
        data class Month(
            @SerializedName("endMonth")
            var endMonth: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("nameAr")
            var nameAr: String? = null,
            @SerializedName("nameEn")
            var nameEn: String? = null,
            @SerializedName("startMonth")
            var startMonth: String? = null
        )
    
        data class User(
            @SerializedName("accessFailedCount")
            var accessFailedCount: Int? = null,
            @SerializedName("clientId")
            var clientId: String? = null,
            @SerializedName("concurrencyStamp")
            var concurrencyStamp: String? = null,
            @SerializedName("displayName")
            var displayName: String? = null,
            @SerializedName("email")
            var email: Any? = null,
            @SerializedName("emailConfirmed")
            var emailConfirmed: Boolean? = null,
            @SerializedName("id")
            var id: String? = null,
            @SerializedName("image")
            var image: String? = null,
            @SerializedName("lockoutEnabled")
            var lockoutEnabled: Boolean? = null,
            @SerializedName("lockoutEnd")
            var lockoutEnd: Any? = null,
            @SerializedName("normalizedEmail")
            var normalizedEmail: Any? = null,
            @SerializedName("normalizedUserName")
            var normalizedUserName: String? = null,
            @SerializedName("passwordHash")
            var passwordHash: Any? = null,
            @SerializedName("phoneNumber")
            var phoneNumber: String? = null,
            @SerializedName("phoneNumberConfirmed")
            var phoneNumberConfirmed: Boolean? = null,
            @SerializedName("securityStamp")
            var securityStamp: String? = null,
            @SerializedName("twoFactorEnabled")
            var twoFactorEnabled: Boolean? = null,
            @SerializedName("userName")
            var userName: String? = null
        )
    }
}