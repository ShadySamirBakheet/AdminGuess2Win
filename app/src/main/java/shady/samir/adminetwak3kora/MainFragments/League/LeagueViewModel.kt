package shady.samir.adminetwak3kora.MainFragments.League

import androidx.lifecycle.*
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.League
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel

import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class LeagueViewModel() : ViewModel() {
    // TODO: Implement the ViewModel

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


