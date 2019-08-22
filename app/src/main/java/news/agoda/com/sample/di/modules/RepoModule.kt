package news.agoda.com.sample.di.modules

import dagger.Binds
import dagger.Module
import news.agoda.com.sample.data.repo.DataRepo
import news.agoda.com.sample.domain.IDataRepo

@Module
abstract class RepoModule {

    @Binds
    abstract fun getRepo(repo:DataRepo):IDataRepo

}