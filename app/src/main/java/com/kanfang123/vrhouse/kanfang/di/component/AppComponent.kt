package com.kanfang123.vrhouse.kanfang.di.component

import android.app.Activity
import android.content.Context
import com.kanfang123.vrhouse.kanfang.di.module.ApplicationModule
import com.kanfang123.vrhouse.kanfang.di.module.RxThreadModule
import com.kanfang123.vrhouse.kanfang.di.module.VRHttpModule
import com.kanfang123.vrhouse.kanfang.di.scope.ApplicationContext
import com.kanfang123.vrhouse.kanfang.di.scope.PerApp
import com.kanfang123.vrhouse.kanfang.view.main.MainActivity
import com.kanfang123.vrhouse.kanfang.view.my.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * description ：
 * created by simon : 2019/5/6
 */
@Singleton
@PerApp
@Component(modules = [ApplicationModule::class])
interface AppComponent {

    @ApplicationContext
    fun getApplication(): Context

}