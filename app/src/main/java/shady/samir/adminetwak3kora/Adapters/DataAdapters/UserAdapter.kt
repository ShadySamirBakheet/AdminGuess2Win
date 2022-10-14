package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shady.samir.adminetwak3kora.Adapters.AdminsItemClickListener
import shady.samir.adminetwak3kora.Models.DataModel.User
import shady.samir.adminetwak3kora.Models.ProfilesResponseModel
import shady.samir.adminetwak3kora.R


class UserAdapter(
    var context: Context,
    var list: List<ProfilesResponseModel.Data>,
    var isSuperAdmin:Boolean
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
lateinit var adminsItemClickListener: AdminsItemClickListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val user = list[position]
        holder.username.text = user.displayName
        holder.userPoint.visibility = View.GONE
        holder.userPhone.text = user.phoneNumber ?: ""
        holder.index.text = "${(position + 1)}"
        Glide.with(context).load(user.image).into(holder.userprofileimage)
        if (isSuperAdmin){
            holder.btndelete.visibility = View.VISIBLE
            holder.btndelete.setOnClickListener {
                adminsItemClickListener.deleteAdmin(user.id.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var username: TextView
        var userPoint: TextView
        var userPhone: TextView
        var index: TextView
        var userprofileimage: ImageView
        var btndelete: View

        init {
            userprofileimage =
                itemView.findViewById(R.id.userprofileimage)
            userPhone = itemView.findViewById(R.id.userPhone)
            userPoint = itemView.findViewById(R.id.userscore)
            username = itemView.findViewById(R.id.username)
            index = itemView.findViewById(R.id.index)
            btndelete = itemView.findViewById(R.id.btnremoveTeam)
        }
    }

    init {
        this.list = list
    }
}

