package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import shady.samir.adminetwak3kora.AddActivities.AddTeamActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.R


class TeamAdapter(
    var context: Context?,
    var list: List<TeamsResponseModel.Team>,
    var isLeague:Boolean,
    var leagueID:Int
) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.teamitam, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val team: TeamsResponseModel.Team = list[position]
        holder.txtteamname.setText(team.name)
        holder.txtteamcountry.setText(team.country)
        Glide.with(context!!).load(team.image).into(holder.imagegteamlogo)
        if (isLeague){

            holder.btnremoveTeam.visibility = View.GONE
            holder.btnremoveTeam.setOnClickListener {
                removeTeamLeague(leagueID, team.id!!)
            }

        }else{
            holder.btnremoveTeam.visibility = View.GONE
            holder.itemView.setOnClickListener {
                val intent = Intent(context, AddTeamActivity::class.java)
                intent.putExtra("id", team.id)
                context?.startActivity(intent)
            }
        }

    }

    private fun removeTeamLeague(leagueID: Int, teamID: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtteamname: TextView
        var txtteamcountry: TextView
        var imagegteamlogo: ImageView
        var btnremoveTeam :FloatingActionButton

        init {
            txtteamname = itemView.findViewById(R.id.txtteamname)
            txtteamcountry = itemView.findViewById(R.id.txtteamcountry)
            imagegteamlogo = itemView.findViewById(R.id.imagegteamlogo)
            btnremoveTeam = itemView.findViewById(R.id.btnremoveTeam)
        }
    }

    init {
        this.list = list
    }
}

