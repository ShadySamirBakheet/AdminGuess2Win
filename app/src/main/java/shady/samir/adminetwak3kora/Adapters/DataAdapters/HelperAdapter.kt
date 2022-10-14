package shady.samir.adminetwak3kora.Adapters.DataAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.AddActivities.AddHelperActivity
import shady.samir.adminetwak3kora.Models.ResponseModel.Help.HelpResponseModel
import shady.samir.adminetwak3kora.R


class HelperAdapter(
    var context: Context,
    var list: List<HelpResponseModel.Data>
) :
    RecyclerView.Adapter<HelperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.help_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val helper = list[position]
        holder.title.setText(helper.title)
        holder.help.setText(helper.description)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddHelperActivity::class.java)
            intent.putExtra("id", helper.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var help: TextView

        init {
            title = itemView.findViewById(R.id.title)
            help = itemView.findViewById(R.id.help)
        }
    }

    init {
        this.list = list
    }
}
