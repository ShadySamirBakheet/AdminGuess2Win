package shady.samir.adminetwak3kora.MainFragments.League

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.Adapters.DataAdapters.LeagueAdapter
import shady.samir.adminetwak3kora.Models.DataModel.League
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class LeagueFragment : Fragment() {

    lateinit var leaguelistView: RecyclerView

    private lateinit var viewModel: LeagueViewModel

    lateinit var helper: HelperView


    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.league_fragment, container, false)

        viewModel = LeagueViewModel()

        helper = HelperView()

        leaguelistView = root.findViewById(R.id.leaguelist)

        leaguelistView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        leaguelistView.layoutManager = layoutManager
/*
        viewModel.responseLeagueList().observe(this, Observer {

            Toast.makeText(context,"Size "+it.body()?.size+"  code "+it.code(),Toast.LENGTH_LONG)

        })
*/
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeagueViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getViewModel(movieId: Int): LeagueViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return LeagueViewModel() as T
            }
        })[LeagueViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseLeagueList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200) {
                leaguelistView.adapter = LeagueAdapter(context, it.body()?.data!!)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
