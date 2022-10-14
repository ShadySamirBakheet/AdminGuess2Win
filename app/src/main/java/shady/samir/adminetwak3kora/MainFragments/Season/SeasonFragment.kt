package shady.samir.adminetwak3kora.MainFragments.Season

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.season_fragment.*
import shady.samir.adminetwak3kora.Adapters.DataAdapters.SeasonAdapter

import shady.samir.adminetwak3kora.R

class SeasonFragment : Fragment() {

    companion object {
        fun newInstance() = SeasonFragment()
    }

    private lateinit var viewModel: SeasonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = SeasonViewModel()
        return inflater.inflate(R.layout.season_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeasonViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseSeasonsList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200){
                seasonlist.adapter = SeasonAdapter(requireContext(),it.body()!!)
                seasonlist.layoutManager =  LinearLayoutManager(context)
            } else{
                Toast.makeText(requireContext(),it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

}
