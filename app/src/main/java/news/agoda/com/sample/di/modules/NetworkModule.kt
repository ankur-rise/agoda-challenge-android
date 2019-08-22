package news.agoda.com.sample.di.modules

import dagger.Module
import dagger.Provides
import news.agoda.com.sample.data.network.Apis
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://api.myjson.com/bins/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getApis(retrofit: Retrofit): Apis {
        return retrofit.create(Apis::class.java)
    }
    @Provides
    fun getRetrofitInstance(gsonConverterFactory: GsonConverterFactory, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }
    @Provides
    fun gethttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS).build()
        return httpClient
    }
    @Provides
    fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}