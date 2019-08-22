package news.agoda.com.sample.di.component

import dagger.Component
import news.agoda.com.sample.di.modules.NetworkModule
import news.agoda.com.sample.di.modules.RepoModule
import news.agoda.com.sample.ui.view.NewsListActivity
import news.agoda.com.sample.ui.view.NewsDetailFragment
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, RepoModule::class])
@Singleton
interface IActivityComponent {

    fun inject(activity: NewsListActivity)
    fun inject(fragment:NewsDetailFragment)

}