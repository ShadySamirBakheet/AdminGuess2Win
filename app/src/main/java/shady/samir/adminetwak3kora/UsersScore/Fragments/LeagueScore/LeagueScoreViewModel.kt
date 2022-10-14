package shady.samir.adminetwak3kora.UsersScore.Fragments.LeagueScore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.LeagueWinnerModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class LeagueScoreViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList(id:Int) : LiveData<Response<LeagueWinnerModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<LeagueWinnerModel>> = liveData {
            val response = retService.GetOrderByBriodicals(id)
            emit(response)
        }

        return responseLiveData
    }
}
