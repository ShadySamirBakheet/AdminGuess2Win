package shady.samir.adminetwak3kora.AddActivities.ActivitiesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.ConfirmEmailResponse
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class AddTeamToLeagueViewModel : ViewModel() {

    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }

    fun responseTeamsList(id: String): LiveData<Response<TeamsResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamsResponseModel>> = liveData {
            val response = retService.GetTeamsByLeague(id)
            emit(response)
        }

        return responseLiveData
    }

    fun getAllTeams(): LiveData<Response<TeamsResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamsResponseModel>> = liveData {
            val response = retService.GetTeams()
            emit(response)
        }

        return responseLiveData
    }

    fun addTeamToLeague(leagueId: String, teamId: String): LiveData<Response<Void>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Void>> = liveData {
            val response = retService.addTeamToLeague(leagueId,teamId)
            emit(response)
        }

        return responseLiveData
    }

    fun deleteTeam(leagueId: String, teamId: String): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response = retService.deleteTeam(leagueId,teamId)
            emit(response)
        }

        return responseLiveData
    }
}