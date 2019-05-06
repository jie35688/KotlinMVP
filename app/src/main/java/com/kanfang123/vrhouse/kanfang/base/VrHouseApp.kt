package com.kanfang123.vrhouse.kanfang.base

import android.app.Application

/**
 * description ：
 * created by simon : 2019/4/17
 */
class VrHouseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        private var instance: VrHouseApp? = null

        fun getInstance(): VrHouseApp {
            return instance!!
        }
    }
}