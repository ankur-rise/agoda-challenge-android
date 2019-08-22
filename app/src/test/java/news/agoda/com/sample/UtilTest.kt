package news.agoda.com.sample

import com.google.gson.Gson
import news.agoda.com.sample.data.models.NewsDataModel
import news.agoda.com.sample.data.models.ResultDataModel
import news.agoda.com.sample.ui.utils.getImageUrlFromResult
import org.junit.Before
import org.junit.Test


class UtilTest{
    lateinit var  model:NewsDataModel

    @Before
    fun setup(){
        val gson = Gson()
        val result = gson.fromJson(NEWS_LIST_RESPONSE, ResultDataModel::class.java)
        model = result.results[0]
    }

    @Test
    fun testImageURL(){
        val actualUrl ="http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-articleInline.jpg"

        val url = getImageUrlFromResult(model)
        assert(actualUrl==url)
    }
}


