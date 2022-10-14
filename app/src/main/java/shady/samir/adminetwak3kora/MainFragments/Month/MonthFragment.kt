package shady.samir.adminetwak3kora.MainFragments.Month

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.month_fragment.*
import shady.samir.adminetwak3kora.Adapters.DataAdapters.MonthAdapter

import shady.samir.adminetwak3kora.R

class MonthFragment : Fragment() {

    companion object {
        fun newInstance() = MonthFragment()
    }

    private lateinit var viewModel: MonthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = MonthViewModel()
        return inflater.inflate(R.layout.month_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MonthViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseWeeksList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200){
                monthslist.adapter = MonthAdapter(requireContext(),it.body()!!)
                monthslist.layoutManager =  LinearLayoutManager(context)
            } else{
                Toast.makeText(requireContext(),it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
