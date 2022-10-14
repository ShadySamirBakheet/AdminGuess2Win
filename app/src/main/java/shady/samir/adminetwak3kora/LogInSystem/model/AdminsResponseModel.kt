package shady.samir.adminetwak3kora.LogInSystem.model


import com.google.gson.annotations.SerializedName

data class AdminsResponseModel(
    @SerializedName("data")
    var `data`: List<Data>? = null
) {
    data class Data(
        @SerializedName("displayName")
        var displayName: String? = null,
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("role")
        var role: String? = null
    )
}