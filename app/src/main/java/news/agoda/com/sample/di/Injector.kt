package news.agoda.com.sample.di

import news.agoda.com.sample.di.component.DaggerIActivityComponent
import news.agoda.com.sample.di.component.IActivityComponent

object Injector {
    private val component: IActivityComponent = DaggerIActivityComponent.builder().build()

    fun getActivityComponent(): IActivityComponent {
        return component
    }
}