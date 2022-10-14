package shady.samir.adminetwak3kora.MainActivities.HomeFiles.ui.gallery

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService


class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    lateinit var delete: Button
    lateinit var retService: SportService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        delete = root.findViewById(R.id.delete)

        delete.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirm")
                .setMessage("Do you really want to Delete all data?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        deleteAll()
                    })
                .setNegativeButton(android.R.string.no, null).show()
        }

        return root
    }

    private fun deleteAll() {
        delete().observe(viewLifecycleOwner, Observer {
            if (it.code() == 200) {
                Toast.makeText(requireContext(), "all data deleted successfully", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()
            }
        })

    }

    fun delete(): LiveData<Response<Void>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Void>> = liveData {
            val response = retService.deleteAll()
            emit(response)
        }

        return responseLiveData
    }
}
