package dev.vespertine.afterhoursmay28

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_queen_details.*
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class QueenDetails : AppCompatActivity() {

    private val disposables = CompositeDisposable()
    private lateinit var lipadapter : LipsyncAdapterLite



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queen_details)

        val  queenIdString = intent.getIntExtra("queen", -1)

        lipadapter = LipsyncAdapterLite(mutableListOf())
        songs_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        songs_list.adapter = lipadapter

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.nokeynoshade.party/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiQueens = retrofit.create(ApiNoKeyNoShade::class.java)

        disposables.add(apiQueens.getSpecificQueen(queenIdString.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {it->showQueen(it)},
                {err -> Log.e("Error Messgage", err.toString())}
            )
        )

    }

    fun showQueen(data: QueenDetailsModels) {
        Picasso.get().load(data.image_url).into(details_imageView)
        lipadapter.setSongs(data.lipsyncs)


    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }
}
