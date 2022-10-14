package shady.samir.adminetwak3kora.MainFragments.Helper

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.helper_fragment.*
import shady.samir.adminetwak3kora.Adapters.DataAdapters.HelperAdapter

import shady.samir.adminetwak3kora.R

class HelperFragment : Fragment() {

    companion object {
        fun newInstance() = HelperFragment()
    }

   var viewModel: HelperViewModel = HelperViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.helper_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HelperViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseHelpList().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200) {
                helperlist.adapter = HelperAdapter(requireContext(), it.body()?.data!!)
                helperlist.layoutManager =  LinearLayoutManager(context)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
