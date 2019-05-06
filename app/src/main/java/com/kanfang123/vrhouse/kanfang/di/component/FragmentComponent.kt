package com.kanfang123.vrhouse.kanfang.di.component

import android.app.Activity
import com.kanfang123.vrhouse.kanfang.di.module.FragmentModule
import com.kanfang123.vrhouse.kanfang.di.scope.PerFragment
import com.kanfang123.vrhouse.kanfang.view.photo.ToTakeFragment
import dagger.Component

/**
 * description ：
 * created by simon : 2019/5/6
 */
@PerFragment
@Component(dependencies = [AppComponent::class],modules = [FragmentModule::class])
interface FragmentComponent {
    fun getActivity(): Activity

    fun inject(fragment: ToTakeFragment)
}