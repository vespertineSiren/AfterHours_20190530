package dev.vespertine.afterhoursmay28

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.specific_queen_list_element_layout.view.*

class LipsyncAdapterLite (
    songs: MutableList<Lipsync> = mutableListOf()
) : BaseRecyclerAdapter<Lipsync>(songs) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.specific_queen_list_element_layout, parent, false))


    class ViewHolder(view : View) : BaseViewHolder<Lipsync>(view) {
        override fun onBind(data: Lipsync) {
            view.song_details.text = "'${data.name}' - ${data.artist}"
            when(data.won) {
                true -> view.specific_card_view.setCardBackgroundColor(Color.GREEN)
                false -> view.specific_card_view.setCardBackgroundColor(Color.DKGRAY)
                else -> {
                    Log.e("Error", "This can't happen.")
                }
            }

        }

    }

    fun setSongs(data: List<Lipsync>) {
        masterList.addAll(data)
        notifyDataSetChanged()
    }

}