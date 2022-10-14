package shady.samir.adminetwak3kora.UsersScore.Fragments.SeasonScore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.MonthWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.SeasonWinnerModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class SeasonScoreViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList(id:Int) : LiveData<Response<SeasonWinnerModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<SeasonWinnerModel>> = liveData {
            val response = retService.GetOrderBySeasons(id)
            emit(response)
        }

        return responseLiveData
    }
}
