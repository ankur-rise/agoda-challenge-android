package news.agoda.com.sample.data.repo

import news.agoda.com.sample.data.models.ResultDataModel
import news.agoda.com.sample.data.network.Apis
import news.agoda.com.sample.domain.IDataRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataRepo @Inject constructor(private val api: Apis, cache:UserCache) : IDataRepo {
    override fun getData(callback: IDataRepo.NetworkCallback<ResultDataModel>) {
        val call = api.getNews()
        call.enqueue(object : Callback<ResultDataModel> {
            override fun onResponse(call: Call<ResultDataModel>, response: Response<ResultDataModel>) {
                val resBody = response.body()
                if (resBody != null) {
                    callback.onSuccess(resBody)
                } else {
                    callback.onError("No data found")
                }
            }

            override fun onFailure(call: Call<ResultDataModel>, t: Throwable) {
                callback.onError(t.message.toString())
            }
        })
    }
}