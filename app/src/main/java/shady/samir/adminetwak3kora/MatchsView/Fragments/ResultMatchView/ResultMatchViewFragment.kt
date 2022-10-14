//package shady.samir.adminetwak3kora.MatchsView.Fragments.ResultMatchView
//
//import androidx.lifecycle.ViewModelProviders
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import shady.samir.adminetwak3kora.Adapters.DataAdapters.MatchAdapter
//import shady.samir.adminetwak3kora.Adapters.DataAdapters.ResultsMatchAdapter
//import shady.samir.adminetwak3kora.MatchsView.Fragments.MatchView.MatchViewViewModel
//import shady.samir.adminetwak3kora.MatchsView.Models.MatchGroup
//
//import shady.samir.adminetwak3kora.R
//
//class ResultMatchViewFragment(var matchGroup:MatchGroup) : Fragment() {
//
//    lateinit var matchviewlist: RecyclerView
//
//    private lateinit var viewModel: ResultMatchViewViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.result_match_view_fragment, container, false)
//
//        matchviewlist = root.findViewById(R.id.matchviewlist)
//
//        matchviewlist.setHasFixedSize(true)
//        val layoutManager = LinearLayoutManager(context)
//        matchviewlist.layoutManager = layoutManager
//
//        val adapter = context?.let { ResultsMatchAdapter(it, matchGroup.matchList) }
//        matchviewlist.setAdapter(adapter)
//
//        return root
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(ResultMatchViewViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}
