package dev.vespertine.afterhoursmay28

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.queen_list_element_layout.view.*


class QueenAdapter : RecyclerView.Adapter<QueenAdapter.QueenViewHolder>(){

    val queenList = mutableListOf<Queen>()

    inner class QueenViewHolder(view: View): RecyclerView.ViewHolder(view){
        val queenImage: ImageView = view.searched_queen_image
        val queenName: TextView = view.searched_queen_name
        val queenWin: TextView = view.searched_queen_winner
        val queenCongen: TextView = view.searched_queens_misscongeniality
        val queenQuote: TextView = view.searched_queens_quote

        @SuppressLint("SetTextI18n")
        fun bindModel(queen: Queen) {
            Picasso.get().load(queen.image_url).into(queenImage)
            queenName.text = queen.name
            queenWin.text = "Winnner? " + queen.winner.toString()
            queenQuote.text =  "Quote: " + queen.quote
            queenCongen.text = "Miss Congeniality? " + queen.missCongeniality.toString()


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenViewHolder{
        return QueenViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.queen_list_element_layout, parent, false))
    }

    override fun onBindViewHolder(viewHolder: QueenViewHolder, index: Int) {
        viewHolder.bindModel(queenList[index])
    }

    override fun getItemCount(): Int = queenList.size

    fun setQueens(data: List<Queen>) {
        queenList.addAll(data)
        notifyDataSetChanged()
    }


}