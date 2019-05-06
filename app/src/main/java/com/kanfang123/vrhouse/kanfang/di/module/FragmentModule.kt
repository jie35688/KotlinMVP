package com.kanfang123.vrhouse.kanfang.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.kanfang123.vrhouse.kanfang.di.scope.ActivityContext
import com.kanfang123.vrhouse.kanfang.di.scope.PerFragment
import dagger.Module
import dagger.Provides

/**
 * description ：
 * created by simon : 2019/5/6
 */
@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @PerFragment
    @ActivityContext
    fun provideActivityContext(): Context = fragment.activity!!

    @Provides
    @PerFragment
    fun provideActivity(): Activity = fragment.activity!!

    @Provides
    @PerFragment
    fun provideFragment(): Fragment = fragment
}