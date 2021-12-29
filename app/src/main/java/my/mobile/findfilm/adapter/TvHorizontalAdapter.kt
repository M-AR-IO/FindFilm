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
import my.mobile.findfilm.api.API
import my.mobile.findfilm.data.Television

class TvHorizontalAdapter(
    val context: Context,
    val items: List<Television>,
    val onSelectData: OnSelectData
): RecyclerView.Adapter<TvHorizontalAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgCover: ImageView
        init {
            imgCover = view.findViewById(R.id.image)
        }
    }
    interface OnSelectData {
        fun onSelect(tv: Television)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_horizontal,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = items[position]
        Glide.with(context)
            .load(API.URLIMAGE + tv.poster_path)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_image)
                .transform(RoundedCorners(16))
            )
            .into(holder.imgCover)
        holder.imgCover.setOnClickListener {
            onSelectData.onSelect(tv)
        }
    }

    override fun getItemCount() = items.size
}