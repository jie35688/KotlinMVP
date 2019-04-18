package com.kanfang123.vrhouse.kanfang.network

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * description ：
 * created by simon : 2019/4/18
 */
class ExceptionManager {
    companion object{
        val TOKEN_INVALID = 1001//token 失效
        val UNKNOWN_ERROR = 1002//未知错误
        val DATA_RESOLVE_ERROR = 1003//数据解析错误
        val NET_ERROR = 1004//网络错误
        val HTTP_ERROR = 1005//HTTP错误


        fun handleException(e: Throwable): VRException {
            var ex: VRException? = null
            if (e is HttpException) {
                val httpException = e
                ex = VRException(HTTP_ERROR)
                if (httpException.code() == 500){
                    ex.errorMsg = "500"
                    return ex
                }
            }else if (e is APIException){

            } else if (e is JsonParseException || e is JSONException || e is ParseException) {
                ex = VRException(DATA_RESOLVE_ERROR)
                ex.errorMsg = "数据处理异常"
            } else if (e is ConnectException || e is SocketTimeoutException || e is UnknownHostException) {
                ex = VRException(NET_ERROR)
                ex.errorMsg = "连接超时"
            } else {
                ex = VRException(UNKNOWN_ERROR)
                ex.errorMsg = "未知错误"
            }
            return ex!!
        }
    }
}