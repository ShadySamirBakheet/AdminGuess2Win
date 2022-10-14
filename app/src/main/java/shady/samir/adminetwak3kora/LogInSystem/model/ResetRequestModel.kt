package shady.samir.adminetwak3kora.LogInSystem.model

import com.google.gson.annotations.SerializedName

class ResetRequestModel(
    @SerializedName("ConfirmPassword")
    var confirmPassword: String? = null,
    @SerializedName("Passwerd")
    var passwerd: String? = null
)