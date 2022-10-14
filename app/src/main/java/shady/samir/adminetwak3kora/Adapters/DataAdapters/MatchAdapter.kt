package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddMatchActivity
import shady.samir.adminetwak3kora.LogInSystem.model.MatchParcelableModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Matchs.MatchsResponseModel
import shady.samir.adminetwak3kora.R
import java.text.Format
import java.text.SimpleDateFormat


class MatchAdapter(
    var context: Context,
    var list: List<MatchsResponseModel.Data>
) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.match_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val match = list[position]
        holder.txtteamone.text = match.firstTeam?.nameEn
        val format: Format = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        holder.txtteamtwo.text = match.secondTeam?.nameEn
        holder.txtdatestart.text = match.time
        if (match.isEnded!!) {
            holder.txtisfinsh.visibility = View.VISIBLE
        } else {
            holder.txtisfinsh.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddMatchActivity::class.java)
            intent.putExtra(
                "match",
                MatchParcelableModel(
                    match.id?:0,
                    match.league?.id ?: 0,
                    match.firstTeam?.id ?: 0,
                    match.secondTeam?.id ?: 0,
                    match.time,
                    if (match.isStarted!!) 1 else 0,
                    if (match.isEnded!!) 1 else 0,
                    match.noOfFirstTeamGoals?:-1,
                    match.noOfSecondTeamGoals?:-1
                )
            )
            context.startActivity(intent)
        }
    }

    private fun findTeam(matchTeamOne: Int, txtteamone: TextView) {
        if (matchTeamOne != null) {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtteamone: TextView
        var txtteamtwo: TextView
        var txtisfinsh: TextView
        var txtdatestart: TextView
        var txtlocation: TextView? = null

        init {
            txtteamone = itemView.findViewById(R.id.txtteamone)
            txtteamtwo = itemView.findViewById(R.id.txtteamtwo)
            txtisfinsh = itemView.findViewById(R.id.txtisfinsh)
            txtdatestart = itemView.findViewById(R.id.txtdatestart)
        }
    }

    init {
        this.list = list
    }
}
