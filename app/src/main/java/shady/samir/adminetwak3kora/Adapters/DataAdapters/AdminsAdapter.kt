package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.Adapters.AdminsItemClickListener
import shady.samir.adminetwak3kora.LogInSystem.model.AdminsResponseModel
import shady.samir.adminetwak3kora.R

class AdminsAdapter(
    var context: Context,
    var list: List<AdminsResponseModel.Data>
) :
    RecyclerView.Adapter<AdminsAdapter.ViewHolder>() {
    lateinit var adminsItemClickListener: AdminsItemClickListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.admin_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val admin = list[position]
        holder.username.text = admin.displayName
        holder.email.text = admin.email ?: ""
        holder.index.text = "${(position + 1)}"
        holder.btndelete.setOnClickListener {
            adminsItemClickListener.deleteAdmin(admin.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var username: TextView
        var email: TextView
        var index: TextView
        var btndelete: View
        init {
            email = itemView.findViewById(R.id.email)
            username = itemView.findViewById(R.id.username)
            index = itemView.findViewById(R.id.index)
            btndelete = itemView.findViewById(R.id.btnremoveTeam)
        }
    }

    init {
        this.list = list
    }
}

