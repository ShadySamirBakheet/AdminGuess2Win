package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("Email")
    var email: String? = null,
    @SerializedName("Passwerd")
    var passwerd: String? = null
)