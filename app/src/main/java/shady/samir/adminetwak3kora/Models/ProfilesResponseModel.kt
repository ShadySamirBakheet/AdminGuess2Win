package shady.samir.adminetwak3kora.Models


import com.google.gson.annotations.SerializedName

data class ProfilesResponseModel(
    @SerializedName("data")
    var `data`: List<Data>? = null
) {
    data class Data(
        @SerializedName("displayName")
        var displayName: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("phoneNumber")
        var phoneNumber: String? = null,
        @SerializedName("role")
        var role: String? = null
    )
}