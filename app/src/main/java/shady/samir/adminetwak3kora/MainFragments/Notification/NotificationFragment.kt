package shady.samir.adminetwak3kora.MainFragments.Notification

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import kotlinx.android.synthetic.main.month_fragment.*
import kotlinx.android.synthetic.main.notification_fragment.*
import retrofit2.Response
import shady.samir.adminetwak3kora.Adapters.DataAdapters.MonthAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.Models.ResponseModel.Notify.NotifyModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Notify.NotifyResponse

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.NotifyRetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.NotifyService
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class NotificationFragment : Fragment() {

    lateinit var notifyTitle:TextInputEditText
    lateinit var notifyMessage:TextInputEditText
    lateinit var send:Button

    lateinit var helper:HelperView

    lateinit var retService: NotifyService

    lateinit var notifyModel: NotifyModel

    var  Key = "Key=AAAAES2CsYw:APA91bFv7ZOHHxkQb7m07sJg7_o56vvFe4c7aW_UWKE_7Tn8nJg0kHFPCqr1fom3LQ7-TfXLh_JdE5g4v7d6bhV_XQqTUxt2PP9fbMWFLZmDY5auHETC-HuBCv5bMT6oPgNgA8IS24Kw"

    companion object {
        fun newInstance() = NotificationFragment()
    }

    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.notification_fragment, container, false)

        retService = NotifyRetrofitInstance
            .getRetrofitInstance()
            .create(NotifyService::class.java)

        send = root.findViewById(R.id.send)
        notifyTitle = root.findViewById(R.id.notifyTitle)
        notifyMessage = root.findViewById(R.id.notifyMessage)

        helper = HelperView()

        send.setOnClickListener {
            sandNotify()
        }

        return root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun sandNotify() {
        val title = notifyTitle.text.toString().trim()
        val msg = notifyMessage.text.toString().trim()

        if (!title.equals("")){
            if (!msg.equals("")){
                notifyModel = NotifyModel(NotifyModel.Data(msg,7,title),"high", "/topics/global")

                sendNotify(notifyModel).observe(this, Observer {
                    if (it.code() == 200){
                       // Toast.makeText(requireContext(),"Notify Send", Toast.LENGTH_LONG).show()
                        val helperView:HelperView = HelperView()
                        activity?.let { it1 ->
                            context?.let { it2 ->
                                helperView.toastAShow(
                                    it2,
                                    it1,"Notify Send",R.drawable.ic_info)
                            }
                        }
                    } else{
                        Toast.makeText(requireContext(),it.message()+"///"+it.code(), Toast.LENGTH_LONG).show()
                    }
                })

            }else{
                activity?.let { context?.let { it1 -> helper.toastAShow(it1, it,"Enter a Message to you Send it",R.drawable.ic_info) } }
            }
        }else{
            activity?.let { context?.let { it1 -> helper.toastAShow(it1, it,"Enter Title of Message you Send it",R.drawable.ic_info) } }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        // TODO: Use the ViewModel
    }


    fun sendNotify(notifyModel: NotifyModel) : LiveData<Response<NotifyResponse>> {
        val responseLiveData: LiveData<Response<NotifyResponse>> = liveData {
            val response = retService.Send(Key,notifyModel)
            emit(response)
        }

        return responseLiveData
    }

}
