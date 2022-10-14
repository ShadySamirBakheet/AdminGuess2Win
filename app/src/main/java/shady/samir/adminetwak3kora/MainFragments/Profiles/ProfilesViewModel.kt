package shady.samir.adminetwak3kora.MainFragments.Profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.ConfirmEmailResponse
import shady.samir.adminetwak3kora.Models.ProfilesResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.AwardsResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class ProfilesViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseProfiles(id: String): LiveData<Response<ProfilesResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ProfilesResponseModel>> = liveData {
            val response = retService.getProfiles(id)
            emit(response)
        }

        return responseLiveData
    }

    fun deleteUser(
        adminId: String,
        userId: String
    ): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response = retService.deleteUser(adminId,userId)
            emit(response)
        }

        return responseLiveData
    }
}
