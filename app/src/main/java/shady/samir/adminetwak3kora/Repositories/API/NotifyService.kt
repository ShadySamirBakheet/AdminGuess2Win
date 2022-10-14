package shady.samir.adminetwak3kora.Repositories.API

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import shady.samir.adminetwak3kora.Models.ResponseModel.Notify.NotifyModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Notify.NotifyResponse

interface NotifyService {

    @POST("send")
    suspend fun Send(@Header("Authorization")key:String,
    @Body notifyModel: NotifyModel): Response<NotifyResponse>

}