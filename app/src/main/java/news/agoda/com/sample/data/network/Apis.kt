package news.agoda.com.sample.data.network

import news.agoda.com.sample.data.models.ResultDataModel
import retrofit2.Call
import retrofit2.http.GET

interface Apis {

    @GET("nl6jh")
    fun getNews(): Call<ResultDataModel>
}