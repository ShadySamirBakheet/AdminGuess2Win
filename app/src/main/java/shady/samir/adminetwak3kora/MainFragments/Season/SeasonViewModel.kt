package shady.samir.adminetwak3kora.MainFragments.Season

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Season
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class SeasonViewModel : ViewModel() {
    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseSeasonsList() : LiveData<Response<List<Season>>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<List<Season>>> = liveData {
            val response = retService.getSeasons()
            emit(response)
        }

        return responseLiveData
    }
}
