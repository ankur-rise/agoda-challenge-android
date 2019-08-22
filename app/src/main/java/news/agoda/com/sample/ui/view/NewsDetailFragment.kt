package news.agoda.com.sample.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import kotlinx.android.synthetic.main.fragment_news_detail.view.*
import news.agoda.com.sample.R
import news.agoda.com.sample.di.Injector
import news.agoda.com.sample.ui.contracts.NewsDetailView
import news.agoda.com.sample.ui.presenters.NewsDetailPresenter
import javax.inject.Inject

class NewsDetailFragment : Fragment(), NewsDetailView {

    lateinit var titleView: TextView

    lateinit var imageView: SimpleDraweeView

    private lateinit var summaryView: TextView

    @Inject
    lateinit var newsDetailPresenter: NewsDetailPresenter

    private var storyURL: String? = null
    private var imageURL: String? = null
    private var title: String? = null
    private var summary: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storyURL = arguments?.getString(KEY_FULL_STORY)
        title = arguments?.getString(KEY_TITLE)
        summary = arguments?.getString(KEY_SUMMARY)
        imageURL = arguments?.getString(KEY_IMAGE_URL)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_news_detail, container, false)

        summaryView = rootView.summary_content
        imageView = rootView.news_image
        titleView = rootView.title
        val component = Injector.getActivityComponent()
        component.inject(this)

        titleView!!.text = title
        summaryView!!.text = summary
        if (imageURL != null) {
            val draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(imageView!!.controller).build()
            imageView!!.controller = draweeController
        }
        newsDetailPresenter.bindView(this)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.full_story_link.setOnClickListener { v: View? -> onFullStoryClicked() }
    }

     fun onFullStoryClicked() {
        newsDetailPresenter.onFullStoryButtonClicked(storyURL)
    }

    override fun openFullStory(storyURI: Uri?) {
        if (storyURI != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = storyURI
            startActivity(intent)
        } else {
            Toast.makeText(activity, "Incorrect url", Toast.LENGTH_SHORT).show()
        }
    }

}
