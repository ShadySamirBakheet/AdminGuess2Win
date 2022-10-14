//package shady.samir.adminetwak3kora.MatchsView.Fragments.MatchView
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
//import shady.samir.adminetwak3kora.Adapters.DataAdapters.TeamAdapter
//import shady.samir.adminetwak3kora.MatchsView.Models.MatchGroup
//
//import shady.samir.adminetwak3kora.R
//
//class MatchViewFragment ( var matchGroup:MatchGroup): Fragment() {
//
//    private lateinit var viewModel: MatchViewViewModel
//
//    lateinit var matchviewlist:RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.match_view_fragment, container, false)
//
//        matchviewlist = root.findViewById(R.id.matchviewlist)
//
//        matchviewlist.setHasFixedSize(true)
//        val layoutManager = LinearLayoutManager(context)
//        matchviewlist.layoutManager = layoutManager
//
//        val adapter = context?.let { MatchAdapter(it, matchGroup.matchList) }
//        matchviewlist.setAdapter(adapter)
//
//        return root
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MatchViewViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}
