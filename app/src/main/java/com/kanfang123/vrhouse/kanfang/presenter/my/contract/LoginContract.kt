package com.kanfang123.vrhouse.kanfang.presenter.my.contract

import com.kanfang123.vrhouse.kanfang.base.BasePresenter
import com.kanfang123.vrhouse.kanfang.base.BaseView

/**
 * description ：
 * created by simon : 2019/4/18
 */
interface LoginContract {

    interface View : BaseView {
        fun httpLoginSuccess()
        fun httpLoginError()
    }

    interface Presenter : BasePresenter {
        fun httpLogin()
    }
}