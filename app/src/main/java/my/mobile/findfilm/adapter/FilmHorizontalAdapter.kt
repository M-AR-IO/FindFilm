package my.mobile.findfilm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import my.mobile.findfilm.R
import my.mobile.findfilm.api.ApiConst
import my.mobile.findfilm.data.Film

class FilmHorizontalAdapter(
    val context: Context,
    val items: List<Film>,
    val onSelectData: OnSelectData
): RecyclerView.Adapter<FilmHorizontalAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgCover: ImageView
        init {
            imgCover = view.findViewById(R.id.image)
        }
    }
    interface OnSelectData {
        fun onSelect(film: Film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_horizontal,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = items[position]
        Glide.with(context)
            .load(ApiConst.URLIMAGE + film.poster_path)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_image)
                .transform(RoundedCorners(16))
            )
            .into(holder.imgCover)
        holder.imgCover.setOnClickListener {
            onSelectData.onSelect(film)
        }
    }

    override fun getItemCount() = items.size
}