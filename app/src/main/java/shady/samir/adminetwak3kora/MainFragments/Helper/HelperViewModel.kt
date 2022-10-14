package shady.samir.adminetwak3kora.MainFragments.Helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.Help.HelpResponseModel
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class HelperViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var retService: SportService

    init {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
    }


    fun responseHelpList() : LiveData<Response<HelpResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<HelpResponseModel>> = liveData {
            val response = retService.getHelpsList()
            emit(response)
        }

        return responseLiveData
    }
}
