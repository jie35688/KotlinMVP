package com.kanfang123.vrhouse.kanfang.model

/**
 * description ：
 * created by simon : 2019/4/18
 */
data class AccessBean(val token: String,val tokenExpireIn: Long, val refreshToken: String,
                      val refreshTokenExpireIn: Long,val userId: String, val userName: String,
                      val region: String, val parentId: String,val billType: Long)