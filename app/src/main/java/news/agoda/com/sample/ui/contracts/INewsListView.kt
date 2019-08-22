package news.agoda.com.sample.ui.contracts

import news.agoda.com.sample.data.models.NewsDataModel

interface INewsListView {
    fun showNews(results: List<NewsDataModel>)
    fun showError(msg:String)
    fun showProgress()
    fun hideProgress()
    fun isConnected(): Boolean
}