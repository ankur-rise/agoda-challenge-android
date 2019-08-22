package news.agoda.com.sample.domain.usecases

import news.agoda.com.sample.data.models.ResultDataModel
import news.agoda.com.sample.domain.IDataRepo
import javax.inject.Inject

open class NewsUseCase @Inject constructor(private val repo: IDataRepo) {

    open fun getNewsList(callback: IDataRepo.NetworkCallback<ResultDataModel>) {
        repo.getData(callback)
    }
}