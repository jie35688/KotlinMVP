package com.kanfang123.vrhouse.kanfang.di.component

import android.app.Activity
import com.kanfang123.vrhouse.kanfang.di.module.ActivityModule
import com.kanfang123.vrhouse.kanfang.di.scope.PerActivity
import com.kanfang123.vrhouse.kanfang.view.my.LoginActivity
import com.kanfang123.vrhouse.kanfang.view.photo.ToTakeFragment
import dagger.Component

/**
 * description ：
 * created by simon : 2019/5/6
 */
@PerActivity
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {

    fun getActivity(): Activity

    fun inject(activity: LoginActivity)

}