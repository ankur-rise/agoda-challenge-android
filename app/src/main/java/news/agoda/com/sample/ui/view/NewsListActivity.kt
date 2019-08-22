package news.agoda.com.sample.ui.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_list.*
import news.agoda.com.sample.R
import news.agoda.com.sample.data.models.NewsDataModel
import news.agoda.com.sample.di.Injector
import news.agoda.com.sample.ui.adapter.NewsListAdapter
import news.agoda.com.sample.ui.contracts.INewsListView
import news.agoda.com.sample.ui.presenters.NewsListPresenter
import news.agoda.com.sample.ui.utils.getImageUrlFromResult
import news.agoda.com.sample.ui.utils.isConnectedToNetwork
import javax.inject.Inject


const val KEY_FULL_STORY = "full_story_url"
const val KEY_TITLE = "full_title"
const val KEY_SUMMARY = "full_summary"
const val KEY_IMAGE_URL = "full_image_url"

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class NewsListActivity : AppCompatActivity(), INewsListView, NewsListAdapter.ListItemClickListener {
    override fun isConnected(): Boolean {
        return this.isConnectedToNetwork()
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    @Inject
    lateinit var presenter: NewsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        val component = Injector.getActivityComponent()
        component.inject(this)
        supportActionBar?.title = getString(R.string.news)
        presenter.bindView(this)
        
        if (item_detail_container != null) {
            twoPane = true
        }
        presenter.getNews()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, news: List<NewsDataModel>) {
        val newsAdapter = NewsListAdapter(this, news)
        recyclerView.adapter = newsAdapter
        newsAdapter.setListener(this)

    }

    override fun showProgress() {
        ll_pb.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        ll_pb.visibility = View.GONE
    }

    override fun onListItemClick(news: NewsDataModel) {
        if (twoPane) {
            val fragment = NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FULL_STORY, news.url)
                    putString(KEY_TITLE, news.title)
                    putString(KEY_SUMMARY, news.abstract)
                    putString(KEY_IMAGE_URL, getImageUrlFromResult(news))
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(this, ItemDetailActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putString(KEY_FULL_STORY, news.url)
                bundle.putString(KEY_TITLE, news.title)
                bundle.putString(KEY_SUMMARY, news.abstract)
                bundle.putString(KEY_IMAGE_URL, getImageUrlFromResult(news))
                putExtras(bundle)
            }
            startActivity(intent)
        }
    }

    override fun showNews(results: List<NewsDataModel>) {
        setupRecyclerView(item_list, results)
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
