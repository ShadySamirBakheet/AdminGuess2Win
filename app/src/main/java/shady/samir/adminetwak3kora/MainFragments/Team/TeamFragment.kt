package shady.samir.adminetwak3kora.MainFragments.Team

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_fragment.*
import shady.samir.adminetwak3kora.Adapters.DataAdapters.TeamAdapter

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class TeamFragment : Fragment() {

    lateinit var helper: HelperView

    lateinit var teamlistView: RecyclerView

    private lateinit var viewModel: TeamViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.team_fragment, container, false)

        teamlistView = root.findViewById(R.id.teamlist)


        viewModel = TeamViewModel()

        helper = HelperView()

        teamlistView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        teamlistView.layoutManager = layoutManager

        /*viewModel.responseTeamList().observe(this, Observer {

            Toast.makeText(context,"Code "+it.code(), Toast.LENGTH_LONG)

        })*/


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun getViewModel(movieId: Int): TeamViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TeamViewModel() as T
            }
        })[TeamViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseTeamList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200) {
                teamlistView.adapter = TeamAdapter(context, it.body()?.data!!, false, 0)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

}
