package shady.samir.adminetwak3kora.MainFragments.Award

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.AwardsResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class AwardViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseAwards() : LiveData<Response<AwardsResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AwardsResponseModel>> = liveData {
            val response = retService.getAwards()
            emit(response)
        }

        return responseLiveData
    }
}
