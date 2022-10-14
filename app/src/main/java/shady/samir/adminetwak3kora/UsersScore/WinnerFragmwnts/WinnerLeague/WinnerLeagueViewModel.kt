package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerLeague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.League
import shady.samir.adminetwak3kora.Models.DataModel.User
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.UsersScore.Models.LeagueScore
import shady.samir.adminetwak3kora.UsersScore.Models.UserScore
import kotlin.collections.ArrayList

class WinnerLeagueViewModel: ViewModel() {

    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList() : LiveData<Response<LeaguesResponseModel>>{
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<LeaguesResponseModel>> = liveData {
            val response = retService.GetLeagues()
            emit(response)
        }

        return responseLiveData
    }


}
