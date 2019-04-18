package com.kanfang123.vrhouse.kanfang.network

/**
 * description ：
 * created by simon : 2019/4/18
 */
class VRException(val code: Int) : Throwable() {
    var error: Int? = null

    var errorMsg: String? = null

    init {
        error = code
    }


}