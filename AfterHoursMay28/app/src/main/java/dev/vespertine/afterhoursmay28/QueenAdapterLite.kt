package dev.vespertine.afterhoursmay28

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.queen_list_element_layout.view.*

class QueenAdapterLite(
    queens: MutableList<Queen> = mutableListOf()
) : BaseRecyclerAdapter<Queen>(queens) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.queen_list_element_layout, parent, false))


    class ViewHolder(view : View) : BaseViewHolder<Queen>(view) {
        override fun onBind(data: Queen){
            view.searched_queen_name.text = data.name
            view.searched_queen_winner.text = "Winnner? " + data.winner.toString()
            view.searched_queens_misscongeniality.text = "Miss Congeniality? " + data.missCongeniality.toString()
            view.searched_queens_quote.text = "Quote: " + data.quote
            Picasso.get().load(data.image_url).into(view.searched_queen_image)
            
        }
    }
}