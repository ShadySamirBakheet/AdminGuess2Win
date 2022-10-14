package shady.samir.adminetwak3kora.AddActivities.ActivitiesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.LeagueModelResponse
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeagueResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class AddMatchViewModel:ViewModel() {

    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList() : LiveData<Response<LeaguesResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<LeaguesResponseModel>> = liveData {
            val response = retService.GetLeagues()
            emit(response)
        }

        return responseLiveData
    }

//    fun responseLeague(id:String) : LiveData<Response<LeagueModelResponse>> {
//        retService = RetrofitInstance
//            .getRetrofitInstance()
//            .create(SportService::class.java)
//        val responseLiveData: LiveData<Response<LeagueModelResponse>> = liveData {
//            val response = retService.getLeagueById(id)
//            emit(response)
//        }
//
//        return responseLiveData
//    }

    fun responseTeamList(league: LeaguesResponseModel.League): LiveData<Response<TeamsResponseModel>>{
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamsResponseModel>> = liveData {
            val response = retService.GetTeamsByLeague(league.id.toString())
            emit(response)
        }

        return responseLiveData
    }

    fun responseTeamOfLeagueList(id:String) : LiveData<Response<TeamsResponseModel>>{
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamsResponseModel>> = liveData {
            val response = retService.GetTeamsByLeague(id)
            emit(response)
        }

        return responseLiveData
    }

}