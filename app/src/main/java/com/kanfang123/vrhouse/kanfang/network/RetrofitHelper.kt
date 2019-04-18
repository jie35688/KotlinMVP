package com.kanfang123.vrhouse.kanfang.network

import com.kanfang123.vrhouse.kanfang.BuildConfig
import com.kanfang123.vrhouse.kanfang.model.VrConstants
import com.kanfang123.vrhouse.kanfang.utils.SPUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * description ：
 * created by simon : 2019/4/18
 */
class RetrofitHelper {
    companion object{
        private var okHttpClient: OkHttpClient? = null

        private fun initOkHttp() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {//在debug 状态下，打印日志
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }

            builder.addInterceptor(Interceptor {
                chain ->
                var request: Request = chain.request()
                if (!"true".equals(request.header("notoken"))) {
                    request = request.newBuilder()
                            .addHeader("Authorization",SPUtil.getString(VrConstants.USER_TOKEN))
                            .method(request.method(),request.body())
                            .build()
                }
                chain.proceed(request)
            })
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.writeTimeout(20, TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true)
            okHttpClient = builder.build()
        }
    }

    init {
        initOkHttp()
    }

    fun <T> getApiService(clz: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://webapi.123kanfang.com/")
                .client(okHttpClient)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return retrofit.create(clz)
    }


}