package com.kanfang123.vrhouse.kanfang.di.module

import android.app.Application
import android.content.Context
import com.kanfang123.vrhouse.kanfang.di.scope.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * description ：
 * created by simon : 2019/5/6
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

}