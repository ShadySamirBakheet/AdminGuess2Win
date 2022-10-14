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
import shady.samir.adminetwak3kora.Adapters.ArrayAdapter.TeamLeagueClickListener
import shady.samir.adminetwak3kora.AddActivities.AddTeamActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.util.*


internal class TeamLeagueAdapter(
    var context: Context,
    var list: List<TeamsResponseModel.Team>,
    var leagueID: String
) : RecyclerView.Adapter<TeamLeagueAdapter.ViewHolder>() {
    lateinit var teamLeagueClickListener: TeamLeagueClickListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.teamitam, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val team = list[position]
        holder.txtteamname.text = team.name
        holder.txtteamcountry.text = team.country
        Glide.with(context).load(team.image).into(holder.imagegteamlogo)
        holder.btndelete.visibility = View.VISIBLE
        holder.btndelete.setOnClickListener {
            teamLeagueClickListener.deleteTeam(team.id.toString())
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddTeamActivity::class.java)
            intent.putExtra("id", team.id)
            context.startActivity(intent)
        }
    }

    private fun deleteLeague(teamID: Int, leagueID: String) {

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtteamname: TextView
        var txtteamcountry: TextView
        var imagegteamlogo: ImageView
        var btndelete: View

        init {
            txtteamname = itemView.findViewById(R.id.txtteamname)
            txtteamcountry = itemView.findViewById(R.id.txtteamcountry)
            imagegteamlogo = itemView.findViewById(R.id.imagegteamlogo)
            btndelete = itemView.findViewById(R.id.btnremoveTeam)
        }
    }

    init {
        this.list = list
    }
}