package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerMonth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class WinnerMonthViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseMonthList() : LiveData<Response<List<Month>>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<List<Month>>> = liveData {
            val response = retService.getMonths()
            emit(response)
        }

        return responseLiveData
    }

}
