package news.agoda.com.sample.ui.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import news.agoda.com.sample.R
import news.agoda.com.sample.data.models.NewsDataModel
import javax.inject.Inject

class NewsListAdapter @Inject
constructor(private val mContext: Context, private var mNewsFeedResults: List<NewsDataModel>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mClickListener: ListItemClickListener? = null

    interface ListItemClickListener {
        fun onListItemClick(news: NewsDataModel)
    }


    fun setListener(clickListener: ListItemClickListener) {
        this.mClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(this.mContext).inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val mediaEntity = mNewsFeedResults!![position]
        viewHolder.newsTitle!!.text = mediaEntity.title
        val multiMediumDataModels = mediaEntity.multimedia
        if (multiMediumDataModels != null && !multiMediumDataModels.isEmpty()) {
            val thumbnailURL = multiMediumDataModels[0].url
            val uri = Uri.parse(thumbnailURL)
            viewHolder.imageView!!.setImageURI(uri)
        }
    }

    override fun getItemCount(): Int {
        return if (mNewsFeedResults != null) mNewsFeedResults!!.size else 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        internal var newsTitle: TextView? = null
        internal var imageView: SimpleDraweeView? = null

        init {
            newsTitle = mView.findViewById(R.id.tv_title)
            imageView = mView.findViewById(R.id.iv)
            this.mView.setOnClickListener { mClickListener!!.onListItemClick(mNewsFeedResults!![adapterPosition]) }
        }
    }
}
