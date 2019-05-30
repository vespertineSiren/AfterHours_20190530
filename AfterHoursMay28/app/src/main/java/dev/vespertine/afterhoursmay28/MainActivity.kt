package dev.vespertine.afterhoursmay28

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()
    private lateinit var queenAdapter: QueenAdapter
    private val autoDisposable = AutoDisposable()
    private lateinit var queenAdapterLite: QueenAdapterLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoDisposable.bindTo(this.lifecycle)

/*        //Adapter Old Way
        queenAdapter = QueenAdapter()
        queen_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        queen_list.adapter = queenAdapter*/

        //Light Adapter
        queenAdapterLite = QueenAdapterLite(mutableListOf())
        queen_list
        queen_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        queen_list.adapter = queenAdapterLite


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.nokeynoshade.party/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiQueens = retrofit.create(ApiNoKeyNoShade::class.java)

        apiQueens.getQueens()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ queenAdapterLite.setQueens(it) },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })
            .addTo(autoDisposable)
    }

/*
            apiQueens.getQueens()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({queenAdapter.setQueens(it)},
                    {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                    })
                .addTo(autoDisposable)

        disposables.add(
            apiQueens.getQueens()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({queenAdapter.setQueens(it)},
                        {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        }))


        apiQueens.getQueens()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Queen>> {
                override fun onNext(t: List<Queen>) {
                    Log.d("onNext", "added queens to adapter")
                    queenAdapter.setQueens(t)

                }

                override fun onComplete() {
                    Log.d("onComplete", "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("onSubscribe", d.toString())
                    disposables.add(d)
                }


                override fun onError(e: Throwable) {
                }

            })
*/

/*        apiQueens.getQueens()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Queen>>{
                override fun onSuccess(t: List<Queen>) {
                    Log.d("onNext", "added queens to adapter")
                    queenAdapter.setQueens(t)                }

                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onError(e: Throwable) {
                }

            })


    }*/

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy(){
        disposables.clear()
        Log.d("onDestroy", "dispoables cleared")
        super.onDestroy()

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
