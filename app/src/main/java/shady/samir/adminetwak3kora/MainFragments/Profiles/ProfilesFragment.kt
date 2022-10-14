package shady.samir.adminetwak3kora.MainFragments.Profiles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.profiles_fragment.*
import shady.samir.adminetwak3kora.Adapters.AdminsItemClickListener
import shady.samir.adminetwak3kora.Adapters.DataAdapters.UserAdapter

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData

class ProfilesFragment : Fragment() {

    companion object {
        fun newInstance() = ProfilesFragment()
    }

    var sharedPreferences: SharedPreferencesData? = null
    private var viewModel: ProfilesViewModel = ProfilesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profiles_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfilesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = SharedPreferencesData(requireContext())
        getProfiles()
    }

    private fun getProfiles() {
        viewModel.responseProfiles(sharedPreferences?.getId() ?: "")
            .observe(viewLifecycleOwner, Observer {
                if (it.code() == 200) {
                    val adapter = UserAdapter(
                        requireContext(),
                        it.body()?.data!!,
                        sharedPreferences?.isSuperAdmin() ?: false
                    )
                    adapter.adminsItemClickListener = object : AdminsItemClickListener {
                        override fun deleteAdmin(adminId: String) {
                            this@ProfilesFragment.deleteAdmin(adminId)
                        }
                    }
                    profileslist.adapter = adapter
                    profileslist.layoutManager = LinearLayoutManager(context)

                } else {
                    Toast.makeText(context, it.message(), Toast.LENGTH_LONG).show()
                }
            })
    }

    fun deleteAdmin(userId: String) {
        viewModel.deleteUser(sharedPreferences?.getId() ?: "", userId).observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(requireContext(), "user deleted successfully", Toast.LENGTH_LONG)
                    .show()
                getProfiles()
            } else {
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }


}
