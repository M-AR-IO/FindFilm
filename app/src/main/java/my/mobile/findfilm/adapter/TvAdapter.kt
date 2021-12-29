package my.mobile.findfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import my.mobile.findfilm.R
import my.mobile.findfilm.api.API
import my.mobile.findfilm.data.Television

class TvAdapter(
    val context: Context,
    val items: List<Television>,
    val onSelectData: OnSelectData
): RecyclerView.Adapter<TvAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cvFilm: CardView
        val imgCover: ImageView
        val tvTitle: TextView
        val tvRealiseDate: TextView
        val tvDeskripsi: TextView
        val ratingbar: RatingBar
        init {
            cvFilm = view.findViewById(R.id.cvFilm)
            imgCover = view.findViewById(R.id.imgPhoto)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvRealiseDate = view.findViewById(R.id.tvRealeseDate)
            tvDeskripsi = view.findViewById(R.id.tvDesc)
            ratingbar = view.findViewById(R.id.ratingBar)
        }
    }
    interface OnSelectData {
        fun onSelect(tv: Television)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_film,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = items[position]

        holder.tvTitle.text = tv.name
        holder.tvRealiseDate.text = tv.realise_date
        holder.tvDeskripsi.text = tv.overview
        holder.ratingbar.let {
            it.numStars = 5
            it.stepSize = 0.5f
            it.rating = tv.vote_average/2
        }
        Glide.with(context)
            .load(API.URLIMAGE + tv.poster_path)
            .apply(RequestOptions()
                .placeholder(R.drawable.cover_image_default)
                .transform(RoundedCorners(16))
            )
            .into(holder.imgCover)
        holder.cvFilm.setOnClickListener {
            onSelectData.onSelect(tv)
        }
    }

    override fun getItemCount() = items.size
}