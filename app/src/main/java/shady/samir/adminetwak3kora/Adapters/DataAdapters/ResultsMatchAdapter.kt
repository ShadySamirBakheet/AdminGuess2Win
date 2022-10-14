package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.ResultsMatchActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Matchs.MatchsResponseModel
import shady.samir.adminetwak3kora.Models.ResultMatch
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.util.*


class ResultsMatchAdapter(
    var context: Context,
    var list: List<MatchsResponseModel.Data>
) :
    RecyclerView.Adapter<ResultsMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.resultsmatchesitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val match = list[position]
        holder.txtteamone.text = match.firstTeam?.nameEn
        holder.txtteamtwo.text = match.secondTeam?.nameEn
        val format0 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.getDefault())
        val format = SimpleDateFormat("hh:MM aa", Locale.getDefault())
        val startSeason = format0.parse(match?.time)
        holder.date.text = format.format(startSeason)
        match.noOfSecondTeamGoals?.let { holder.txtscoreteamtwo.setText(it.toString()) }
        match.noOfFirstTeamGoals?.let { holder.txtscoreteamone.setText(it.toString()) }
        if (match.isEnded!!) {
            holder.txtisfinsh.visibility = View.VISIBLE
        } else {
            holder.txtisfinsh.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ResultsMatchActivity::class.java)
            intent.putExtra("id", match.id)
            intent.putExtra("match",ResultMatch(match.firstTeam?.nameEn,match.secondTeam?.nameEn))
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
        var txtscoreteamone: TextView
        var txtscoreteamtwo: TextView
        var date: TextView

        init {
            txtteamone = itemView.findViewById(R.id.txtteamone)
            txtteamtwo = itemView.findViewById(R.id.txtteamtwo)
            txtisfinsh = itemView.findViewById(R.id.txtisfinsh)
            txtscoreteamone = itemView.findViewById(R.id.txtscoreteamone)
            txtscoreteamtwo = itemView.findViewById(R.id.txtscoreteamtwo)
            date = itemView.findViewById(R.id.date)
        }
    }

    init {
        this.list = list
    }
}
