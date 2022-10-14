package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class ResetPassordResponseModel(
    @SerializedName("randomKey")
    var randomKey: String? = null,
    @SerializedName("user")
    var user: User? = null
) {
    data class User(
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("id")
        var id: String? = null
    )
}