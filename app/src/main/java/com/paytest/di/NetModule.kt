package com.paytest.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.paytest.api.ApiCall
import com.paytest.api.HeaderInterceptor
import com.paytest.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {
    private var retrofit: Retrofit? = null

    @Provides
    @Singleton
    fun getService(): ApiCall {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
//                        .authenticator(TokenAuthenticator())
                        .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                        .build()
                )
                .build()
        }
        return retrofit!!.create(ApiCall::class.java)
    }
}