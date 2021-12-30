package my.mobile.findfilm.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import my.mobile.findfilm.R
import my.mobile.findfilm.data.Trailer
import kotlin.contracts.contract

class TrailerAdapter(
    val context: Context,
    val items: List<Trailer>
): RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val btn: Button
        init {
            btn = view.findViewById(R.id.btnTrailer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_trailer,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trailer = items[position]

        holder.btn.text = trailer.type
        holder.btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/watch?v=" + trailer.key)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size
}