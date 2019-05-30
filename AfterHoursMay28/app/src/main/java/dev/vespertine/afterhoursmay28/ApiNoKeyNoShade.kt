package dev.vespertine.afterhoursmay28
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiNoKeyNoShade {

    //base: http://www.nokeynoshade.party/api/
    @GET("queens/all")
    fun getQueens(): Single<List<Queen>>


}