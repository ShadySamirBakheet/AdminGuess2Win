package shady.samir.adminetwak3kora.MainFragments.ResultsMatches

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response
import shady.samir.adminetwak3kora.Adapters.DataAdapters.ResultsMatchAdapter
import shady.samir.adminetwak3kora.MainFragments.Matches.MatchesViewModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Matchs.MatchsResponseModel

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.text.SimpleDateFormat
import java.util.*

class ResultsMatchesFragment : Fragment() {

    lateinit var matchslist: RecyclerView
    lateinit var dateDay: TextView

    lateinit var cDate: Calendar
    lateinit var retService: SportService

    companion object {
        fun newInstance() = ResultsMatchesFragment()
    }

    private lateinit var viewModel: ResultsMatchesViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.results_matches_fragment, container, false)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        viewModel = ResultsMatchesViewModel()

        matchslist = root.findViewById(R.id.matchslist)
        dateDay = root.findViewById(R.id.dateDay)

        matchslist.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        matchslist.layoutManager = layoutManager

        val sdf = SimpleDateFormat("yyyy-MM-dd")

        dateDay.setOnClickListener {
            selectDate()
        }

        dateDay.text = sdf.format(Date())

        getMatches(sdf.format(Date()))


        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultsMatchesViewModel::class.java)
        // TODO: Use the ViewModel
    }


    @SuppressLint("FragmentLiveDataObserve")
    private fun getMatches(date: String) {
        getMatchesApi(date).observe(this, androidx.lifecycle.Observer {
            if (it.code() == 200) {
                val adapter = context?.let { it1 ->
                    ResultsMatchAdapter(
                        it1,
                        it.body()?.data as List<MatchsResponseModel.Data>
                    )
                }

                matchslist.adapter = adapter

            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getMatchesApi(date:String): LiveData<Response<MatchsResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<MatchsResponseModel>> = liveData {
            val response = retService.GetMatchs(date)
            emit(response)
        }

        return responseLiveData
    }


    private fun getViewModel(): MatchesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MatchesViewModel() as T
            }
        })[MatchesViewModel::class.java]
    }

    private fun selectDate() {
        val calendar = Calendar.getInstance()

        cDate = Calendar.getInstance()
        context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                    cDate.set(i, i1, i2)

                    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    dateDay.text = format.format(cDate.time)

                    getMatches(format.format(cDate.time))

                },
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }


}
