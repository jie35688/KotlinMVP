package com.kanfang123.vrhouse.kanfang.view.my

import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import com.kanfang123.vrhouse.kanfang.R
import com.kanfang123.vrhouse.kanfang.base.BaseActivity
import com.kanfang123.vrhouse.kanfang.base.BasePresenter
import com.kanfang123.vrhouse.kanfang.presenter.my.LoginPresenter
import com.kanfang123.vrhouse.kanfang.presenter.my.contract.LoginContract

/**
 * description ：
 * created by simon : 2019/4/18
 */
class LoginActivity : BaseActivity<LoginPresenter>(),LoginContract.View {

    @BindView(R.id.tv)
    lateinit var mTV: TextView

    override fun initPresenter() {
        mPresenter = LoginPresenter()
    }

    override fun initContentView(): Int = R.layout.activity_login

    override fun initEventAndData() {
        mTV.setOnClickListener {
            mPresenter.httpLogin()
        }
    }

    override fun isInitEventBus(): Boolean = false

    override fun httpLoginSuccess() {
        mTV.text = "成功"
    }

    override fun httpLoginError() {
        mTV.text = "失败"
    }
}