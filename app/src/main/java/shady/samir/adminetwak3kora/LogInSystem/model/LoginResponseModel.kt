package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("user")
    var user: User? = null
) {
    data class User(
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("roles")
        var roles: List<String?>? = null
    )
}