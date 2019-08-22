package news.agoda.com.sample.domain

import news.agoda.com.sample.data.models.ResultDataModel

interface IDataRepo {

    fun getData(callback: NetworkCallback<ResultDataModel>)


    interface NetworkCallback<in T> {
        fun onSuccess(data:T)
        fun onError(msg:String)
    }

}