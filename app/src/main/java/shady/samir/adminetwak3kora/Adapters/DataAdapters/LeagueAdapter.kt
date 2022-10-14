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
import shady.samir.adminetwak3kora.AddActivities.AddLeagueActivity
import shady.samir.adminetwak3kora.AddActivities.AddTeamToLeagueActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.R
import java.text.SimpleDateFormat
import java.util.*


class LeagueAdapter(
    var context: Context?,
    var list: List<LeaguesResponseModel.League>
) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.league_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val league: LeaguesResponseModel.League? = list?.get(position)
        holder.txtleauguename.setText(league?.name)
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        val startDate = format.parse(league?.startDate)
        holder.txtstaertdate.text = format.format(startDate)
        val endDate = format.parse(league?.endDate)
        holder.txtenddate.text = format.format(endDate)
        holder.btnAddTeams.setOnClickListener {
            val intent = Intent(context, AddTeamToLeagueActivity::class.java)
            intent.putExtra("id", league?.id)
            context?.startActivity(intent)
        }
        Glide.with(context!!).load(league?.image).into(holder.imagegleaguelogo)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddLeagueActivity::class.java)
            intent.putExtra("id", league?.id)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtleauguename: TextView
        var txtstaertdate: TextView
        var txtenddate: TextView
        var imagegleaguelogo: ImageView
        var btnAddTeams: FloatingActionButton

        init {
            txtleauguename = itemView.findViewById(R.id.txtleauguename)
            txtstaertdate = itemView.findViewById(R.id.txtstaertdate)
            txtenddate = itemView.findViewById(R.id.txtenddate)
            imagegleaguelogo = itemView.findViewById(R.id.imagegleaguelogo)
            btnAddTeams = itemView.findViewById(R.id.btnAddTeams)
        }
    }

    init {
        this.list = list
    }
}
