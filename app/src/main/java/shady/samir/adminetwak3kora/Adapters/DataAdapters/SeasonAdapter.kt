package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddSeasonActivity
import shady.samir.adminetwak3kora.Models.DataModel.Season
import shady.samir.adminetwak3kora.R
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*


class SeasonAdapter(
    var context: Context,
    var list: List<Season>
) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.week_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val season: Season = list[position]
        val format0 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.getDefault())
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val startSeason = format0.parse(season?.startSeason)
        val endSeason = format0.parse(season?.endSeason)

        holder.txtWeekName.text= season.nameEn.toString()
        holder.txtweekStart.text =format.format(startSeason)
        holder.txtweekEnd.text =format.format(endSeason)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddSeasonActivity::class.java)
            intent.putExtra("id", season.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtweekStart: TextView
        var txtweekEnd: TextView
        var txtWeekName: TextView

        init {
            txtweekStart = itemView.findViewById(R.id.txtStart)
            txtweekEnd = itemView.findViewById(R.id.txtEnd)
            txtWeekName = itemView.findViewById(R.id.txtName)
        }
    }

    init {
        this.list = list
    }
}
