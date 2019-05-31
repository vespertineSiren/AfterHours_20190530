package dev.vespertine.afterhoursmay28
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiNoKeyNoShade {

    //base: http://www.nokeynoshade.party/api/
    @GET("queens/all")
    fun getQueens(): Single<List<Queen>>


    @GET("queens/{id}")
    fun getSpecificQueen(@Path("id") id: String): Single<QueenDetailsModels>
}