package news.agoda.com.sample

import android.content.Context
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import news.agoda.com.sample.data.models.NewsDataModel
import news.agoda.com.sample.data.models.ResultDataModel
import news.agoda.com.sample.domain.IDataRepo
import news.agoda.com.sample.domain.usecases.NewsUseCase
import news.agoda.com.sample.ui.contracts.INewsListView
import news.agoda.com.sample.ui.presenters.NewsListPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class NewsListPresenterTest {

    private lateinit var presenter: NewsListPresenter
    @Mock
    lateinit var useCase: NewsUseCase
    @Mock
    lateinit var view: INewsListView
    @Mock
    lateinit var context:Context

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = NewsListPresenter(useCase)
        presenter.bindView(view)
    }

    @Test
    fun testGetNews_No_Internet() {

        whenever(view.isConnected()).thenReturn(false)

         presenter.getNews()

        verify(view).showError("No internet")

    }

    @Test
    fun testGetNews_Success() {

        whenever(view.isConnected()).thenReturn(true)

        presenter.getNews()

        argumentCaptor<IDataRepo.NetworkCallback<ResultDataModel>>().apply {

        verify(useCase).getNewsList(capture())
            val result = ResultDataModel()
            val news = ArrayList<NewsDataModel>()
            result.results = news
            firstValue.onSuccess(result)
            verify(view).showNews(news)
        }

    }

    @Test
    fun testGetNews_Error() {

        whenever(view.isConnected()).thenReturn(true)

        presenter.getNews()

        argumentCaptor<IDataRepo.NetworkCallback<ResultDataModel>>().apply {

            verify(useCase).getNewsList(capture())
            val msg = "Internal server error"
            firstValue.onError(msg)
            verify(view).showError(msg)
        }

    }

}