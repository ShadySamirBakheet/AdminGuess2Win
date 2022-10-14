package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerWeek

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Season
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class WinnerWeekViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseLeagueList() : LiveData<Response<List<Week>>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<List<Week>>> = liveData {
            val response = retService.getWeeks()
            emit(response)
        }

        return responseLiveData
    }
}
