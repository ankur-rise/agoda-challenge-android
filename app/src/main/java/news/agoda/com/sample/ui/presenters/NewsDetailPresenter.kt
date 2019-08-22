package news.agoda.com.sample.ui.presenters

import android.net.Uri

import javax.inject.Inject

import news.agoda.com.sample.ui.contracts.NewsDetailView

class NewsDetailPresenter @Inject
constructor() : BasePresenter<NewsDetailView>() {

    fun onFullStoryButtonClicked(storyURL: String?) {
        var storyUri: Uri? = null
        if (storyURL != null) {
            storyUri = Uri.parse(storyURL)
        }
        if (view != null) {
            view!!.openFullStory(storyUri)
        }
    }
}
