package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class AddAdminRequestModel(
    @SerializedName("ConfirmPassword")
    var confirmPassword: String? = null,
    @SerializedName("Email")
    var email: String? = null,
    @SerializedName("Passwerd")
    var passwerd: String? = null,
    @SerializedName("UserName")
    var userName: String? = null
)