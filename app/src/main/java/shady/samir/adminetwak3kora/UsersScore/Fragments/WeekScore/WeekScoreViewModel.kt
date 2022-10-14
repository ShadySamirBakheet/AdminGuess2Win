package shady.samir.adminetwak3kora.UsersScore.Fragments.WeekScore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.SeasonWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.WeekWinnerModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class WeekScoreViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList(id:Int) : LiveData<Response<WeekWinnerModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<WeekWinnerModel>> = liveData {
            val response = retService.GetOrderByWeeks(id)
            emit(response)
        }

        return responseLiveData
    }
}
