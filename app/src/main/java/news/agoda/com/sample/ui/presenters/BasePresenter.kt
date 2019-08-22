package news.agoda.com.sample.ui.presenters

open class BasePresenter<V> {
    var view:V? = null

    fun bindView(view:V) {
        this.view = view
    }
}