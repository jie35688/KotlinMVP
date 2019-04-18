package com.kanfang123.vrhouse.kanfang.presenter.my

import com.kanfang123.vrhouse.kanfang.base.RxPresenter
import com.kanfang123.vrhouse.kanfang.network.RetrofitHelper
import com.kanfang123.vrhouse.kanfang.network.TestApi
import com.kanfang123.vrhouse.kanfang.presenter.my.contract.LoginContract
import com.kanfang123.vrhouse.kanfang.utils.RxUtil

/**
 * description ：
 * created by simon : 2019/4/18
 */
class LoginPresenter : RxPresenter<LoginContract.View>(), LoginContract.Presenter {

    private val mApi: TestApi = (RetrofitHelper()).getApiService(TestApi::class.java)


    override fun httpLogin() {
        var subscription = mApi.getAccessToken(true,"lly","123456","")
                .compose(RxUtil.applySchedulers())
                .compose(RxUtil.handleResult())
                .subscribe({
                    mView?.httpLoginSuccess()
                },{
                    mView?.httpLoginError()
                })
        addSubscrebe(subscription)
    }

}