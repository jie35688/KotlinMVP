package com.kanfang123.vrhouse.kanfang.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.kanfang123.vrhouse.kanfang.R
import com.kanfang123.vrhouse.kanfang.ui.VrProgressDialog
import com.kanfang123.vrhouse.kanfang.view.my.LoginActivity
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.concurrent.timerTask

/**
 * description ：Activity基类
 * created by simon : 2019/4/18
 */
abstract class BaseActivity <T : BasePresenter> : AppCompatActivity(), BaseView{
    lateinit var mPresenter: T
    private lateinit var mContext: Activity
    private lateinit var mUnBinder: Unbinder
    private var mEmptyView: View? = null
    private val loadingTimer = Timer()
    private var loadingTask: TimerTask? = null
    private lateinit var mProgressDialog: VrProgressDialog
    private val loadingHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            dissLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT// 竖屏
        if (isInitEventBus()) EventBus.getDefault().register(this)
        mUnBinder = ButterKnife.bind(this)
        mContext = this
        initPresenter()
        mPresenter.attachView(this)
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        dissLoading()
        if (isInitEventBus()) EventBus.getDefault().unregister(this)
        mPresenter.detachView()
        mUnBinder.unbind()
    }

    override fun showLoading() {
            mProgressDialog = VrProgressDialog.Builder(mContext).create(null)

        if (loadingTask == null) {
            loadingTask = timerTask {
                val message = Message()
                message.what = 1
                loadingHandler.sendMessage(message)
            }
        }else
            loadingTask?.cancel()

        if (!mProgressDialog.isShowing){
            mProgressDialog.show()
            loadingTimer.schedule(loadingTask, 500)
        }


    }

    override fun dissLoading() {
        mProgressDialog.dismiss()
        try {
            loadingTask?.cancel()
            loadingTask = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected abstract fun initPresenter()
    protected abstract fun initContentView(): Int
    protected abstract fun initEventAndData()
    protected abstract fun isInitEventBus(): Boolean

    @SuppressLint("InflateParams")
    fun getEmptyView(): View {
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty_view, null)
            mEmptyView?.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return mEmptyView!!
    }

    override fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun close() {
        finish()
    }

}