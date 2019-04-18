package com.kanfang123.vrhouse.kanfang.base

/**
 * description ：presenter基类
 * created by simon : 2019/4/17
 */
interface BasePresenter {

    fun attachView(view: BaseView)

    fun detachView()
}
