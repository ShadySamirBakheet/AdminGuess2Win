package shady.samir.adminetwak3kora.MainFragments.Week

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.award_fragment.*
import kotlinx.android.synthetic.main.week_fragment.*
import shady.samir.adminetwak3kora.Adapters.DataAdapters.WeekAdapter

import shady.samir.adminetwak3kora.R

class WeekFragment : Fragment() {

    companion object {
        fun newInstance() = WeekFragment()
    }

    private lateinit var viewModel: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = WeekViewModel()
        return inflater.inflate(R.layout.week_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WeekViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseWeeksList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200){
                weeklist.adapter = WeekAdapter(requireContext(),it.body()!!)
                weeklist.layoutManager =  LinearLayoutManager(context)
            } else{
                Toast.makeText(requireContext(),it.message(),Toast.LENGTH_LONG).show()
            }
        })
    }

}
