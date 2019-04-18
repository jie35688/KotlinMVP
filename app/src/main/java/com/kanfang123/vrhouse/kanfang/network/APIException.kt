package com.kanfang123.vrhouse.kanfang.network

/**
 * description ：
 * created by simon : 2019/4/18
 */
class APIException(code: String,msg: String) : Exception() {

    var code: String? = null
    var msg: String? = null

    init {
        this.code = code
        this.msg = msg
    }

}