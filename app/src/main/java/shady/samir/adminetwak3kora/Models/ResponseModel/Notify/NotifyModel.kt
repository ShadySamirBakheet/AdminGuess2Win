package shady.samir.adminetwak3kora.Models.ResponseModel.Notify


import com.google.gson.annotations.SerializedName

data class NotifyModel(
    @SerializedName("data")
    var `data`: Data? = null,
    @SerializedName("priority")
    var priority: String = "high",
    @SerializedName("to")
    var to: String = "/topics/global"
) {
    data class Data(
        @SerializedName("body")
        var body: String? = null,
        @SerializedName("offer_id")
        var offerId: Int? = 7,
        @SerializedName("title")
        var title: String? = null
    )
}