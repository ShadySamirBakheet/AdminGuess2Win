package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.LeagueWinnerModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.UsersScore.Models.UserScore
import java.util.*


class ScoreAdapter(
    var context: Context,
    var list: LeagueWinnerModel
) :
    RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    private fun arrangeWinner(list: List<UserScore?>): List<UserScore?> {
        val scoreList: List<UserScore?> = ArrayList<UserScore>(list)
         //sort List
        return scoreList
    }

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
        val leagueWinnerModelItem = list[position]!!
        val user: LeagueWinnerModel.LeagueWinnerModelItem.User? = leagueWinnerModelItem.user
        holder.username.setText(user?.userName)
        if (leagueWinnerModelItem.score!! > 0) {
            holder.userPoint.setText(leagueWinnerModelItem.score.toString() + "")
        } else {
            holder.userPoint.text = 0.toString() + ""
        }
        holder.userPhone.setText(user?.phoneNumber)
        holder.index.visibility = View.VISIBLE
        holder.index.setText((position + 1).toString() )
        Glide.with(context).load(user?.image).into(holder.userprofileimage)
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

        init {
            userprofileimage =
                itemView.findViewById(R.id.userprofileimage)
            userPhone = itemView.findViewById(R.id.userPhone)
            userPoint = itemView.findViewById(R.id.userscore)
            username = itemView.findViewById(R.id.username)
            index = itemView.findViewById(R.id.index)
        }
    }

    init {
        this.list = list
    }
}
