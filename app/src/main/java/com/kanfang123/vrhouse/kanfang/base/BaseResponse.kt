package com.kanfang123.vrhouse.kanfang.base

/**
 * description ：网络请求返回的基类
 * created by simon : 2019/4/17
 */
data class BaseResponse<T>(val state: String, val payload: T)
