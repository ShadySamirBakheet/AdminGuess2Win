package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class ChangePasswordRequestModel(
    @SerializedName("ConfirmPassword")
    var confirmPassword: String? = null,
    @SerializedName("OldPasswerd")
    var oldPasswerd: String? = null,
    @SerializedName("Passwerd")
    var passwerd: String? = null
)