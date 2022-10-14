package shady.samir.adminetwak3kora.Models.ResponseModel.Notify


import com.google.gson.annotations.SerializedName

data class NotifyResponse(
    @SerializedName("message_id")
    var messageId: Long? = null
)