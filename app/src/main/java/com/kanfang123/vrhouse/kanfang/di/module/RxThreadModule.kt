package com.kanfang123.vrhouse.kanfang.di.module

import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * description ：
 * created by simon : 2019/5/6
 */
@Module
open class RxThreadModule {
    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }

    @Singleton
    @Provides
    @Named(mainThread)
    open fun provideAndroidSchedulers(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    @Named(ioThread)
    open fun provideSchedulersIO(): Scheduler = Schedulers.io()
}