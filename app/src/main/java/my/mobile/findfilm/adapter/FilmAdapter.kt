package my.mobile.findfilm.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import my.mobile.findfilm.R
import my.mobile.findfilm.api.ApiConst
import my.mobile.findfilm.data.Film
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class FilmAdapter(
    val context: Context,
    val items: List<Film>,
    val onSelectData: OnSelectData
): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
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
        fun onSelect(film: Film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_film,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = items[position]

        holder.tvTitle.text = film.title
        val date = film.release_date.let {
            var hasil = "Unknown"
            val regex = """[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]""".toRegex()
            if (regex.matches(it)){
                val dateTime = LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val unix = dateTime.atStartOfDay(ZoneId.systemDefault())
                hasil = unix.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
            }
            hasil
        }
        holder.tvRealiseDate.text = date
        holder.tvDeskripsi.text = film.overview
        holder.ratingbar.let {
            it.numStars = 5
            it.stepSize = 0.5f
            it.rating = film.rating/2
        }
        Glide.with(context)
            .load(ApiConst.URLIMAGE + film.poster_path)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_image)
                .transform(RoundedCorners(16))
            )
            .into(holder.imgCover)
        holder.cvFilm.setOnClickListener {
            onSelectData.onSelect(film)
        }
    }

    override fun getItemCount() = items.size
}