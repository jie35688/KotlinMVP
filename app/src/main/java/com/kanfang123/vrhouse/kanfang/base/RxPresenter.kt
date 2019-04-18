package com.kanfang123.vrhouse.kanfang.base

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * description ：
 * created by simon : 2019/4/17
 */
@Suppress("UNCHECKED_CAST")
open class RxPresenter<T : BaseView> : BasePresenter {

    protected var mView: T? = null
    private var mCompositeSubscription: CompositeSubscription? = null

    override fun detachView() {
        mView = null
        unSubscribe()

    }

    override fun attachView(view: BaseView) {
        mView = view as T
    }

    protected fun addSubscrebe(subscription: Subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeSubscription()
        }
        mCompositeSubscription?.add(subscription)
    }

    private fun unSubscribe() {
        mCompositeSubscription?.clear()
        mCompositeSubscription?.unsubscribe()
    }
}