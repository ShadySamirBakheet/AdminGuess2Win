package shady.samir.adminetwak3kora.MainFragments.Team

import androidx.lifecycle.*
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Team
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class TeamViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseTeamList() : LiveData<Response<TeamsResponseModel>>{
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamsResponseModel>> = liveData {
            val response = retService.GetTeams()
            emit(response)
        }

        return responseLiveData
    }

    val teamList : LiveData<List<Team>> by lazy {
            val teamlist : ArrayList<Team> = ArrayList()

            teamlist?.add(
                Team(
                    1,
                    "NameAR",
                    "NameEN",
                    "null",
                    "Eygpt",
                    "eygpten"
                )
            )
            teamlist?.add(
                Team(
                    2,
                    "NameAR",
                    "NameEN",
                    "null",
                    "Eygpt",
                    "eygpten"
                )
            )
            teamlist?.add(
                Team(
                    3,
                    "NameAR",
                    "NameEN",
                    "null",
                    "Eygpt",
                    "eygpten"
                )
            )
            teamlist?.add(
                Team(
                    4,
                    "NameAR",
                    "NameEN",
                    "null",
                    "Eygpt",
                    "eygpten"
                )
            )

            teamlist?.toList().let {
                postValue(it)
            }
        }

    private fun postValue(it: List<Team>): LiveData<List<Team>> {

        val liveData : MutableLiveData<List<Team>> =  MutableLiveData()
        liveData.run {
            postValue(it)
        }

        return liveData
    }

}