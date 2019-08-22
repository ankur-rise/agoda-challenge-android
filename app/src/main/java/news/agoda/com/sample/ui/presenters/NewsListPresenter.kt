package news.agoda.com.sample.ui.presenters

import news.agoda.com.sample.data.models.ResultDataModel
import news.agoda.com.sample.domain.IDataRepo
import news.agoda.com.sample.domain.usecases.NewsUseCase
import news.agoda.com.sample.ui.contracts.INewsListView
import javax.inject.Inject

class NewsListPresenter @Inject constructor(private val useCase: NewsUseCase) : BasePresenter<INewsListView>() {

    fun getNews() {
        if(!view!!.isConnected()){
            view?.showError("No internet")
            return
        }
        view?.showProgress()

        useCase.getNewsList(object : IDataRepo.NetworkCallback<ResultDataModel> {
            override fun onSuccess(data: ResultDataModel) {
                view!!.showNews(data.results)
                view?.hideProgress()
            }

            override fun onError(msg: String) {
                view!!.showError(msg)
                view?.hideProgress()
            }

        })
    }

}