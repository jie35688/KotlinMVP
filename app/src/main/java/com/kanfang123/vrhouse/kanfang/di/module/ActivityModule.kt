package com.kanfang123.vrhouse.kanfang.di.module

import android.app.Activity
import android.content.Context
import com.kanfang123.vrhouse.kanfang.di.scope.ActivityContext
import com.kanfang123.vrhouse.kanfang.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * description ：
 * created by simon : 2019/5/6
 */
@Module
class ActivityModule(private val activity: Activity) {

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @PerActivity
    @Provides
    fun provideActivity(): Activity = activity
}