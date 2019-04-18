package com.kanfang123.vrhouse.kanfang.network

import com.kanfang123.vrhouse.kanfang.base.BaseResponse
import com.kanfang123.vrhouse.kanfang.model.AccessBean
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable

/**
 * description ：
 * created by simon : 2019/4/18
 */
interface TestApi {
    @GET("v1/user/GetAccessToken")
    fun getAccessToken(@Header("notoken") notoken: Boolean, @Query("authKey") authKey: String, @Query("authValue") authValue: String, @Query("clientEdtion") clientEdtion: String): Observable<BaseResponse<AccessBean>>
}