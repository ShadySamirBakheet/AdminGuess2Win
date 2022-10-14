package shady.samir.adminetwak3kora.MainFragments.Award

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
import shady.samir.adminetwak3kora.Adapters.DataAdapters.AwardAdapter

import shady.samir.adminetwak3kora.R

class AwardFragment : Fragment() {

    companion object {
        fun newInstance() = AwardFragment()
    }

   var viewModel: AwardViewModel = AwardViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.award_fragment, container, false)

        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AwardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseAwards().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200){
                awardlist.adapter = AwardAdapter(requireContext(),it.body()?.data!!)
                awardlist.layoutManager =  LinearLayoutManager(context)

            }
            else{
                Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
