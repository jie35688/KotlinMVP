package com.kanfang123.vrhouse.kanfang.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
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
 * description ：Fragment基类
 * created by simon : 2019/4/17
 */
abstract class BaseFragment<T : BasePresenter> : Fragment(), BaseView {

    lateinit var mPresenter: T
    lateinit var mView: View
    var mActivity: Activity? = null
    var mContext: Context? = null
    private var mUnBinder: Unbinder? = null
    private var isInited = false
    private var mEmptyView: View? = null
    private var mProgressDialog: VrProgressDialog? = null
    private val loadingTimer = Timer()
    private var loadingTask: TimerTask? = null
    private var loadingHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            dissLoading()
        }
    }


    override fun onAttach(context: Context?) {
        mActivity = activity
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(initContentView(),null)
        initPresenter()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mUnBinder = ButterKnife.bind(this,view)
        if (isInitEventBus()) {
            EventBus.getDefault().register(this)
        }
        if (savedInstanceState == null) {
            if (!isHidden){
                isInited = true
                initEventAndData()
            }
        } else{
            isInited = true
            initEventAndData()
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            dissLoading()
        }
        if (!isInited && !hidden){
            isInited = true
            initEventAndData()
        }
    }

    override fun onDestroyView() {
        dissLoading()
        mUnBinder?.unbind()
        if (isInitEventBus())
            EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }



    override fun showLoading() {
        if (mProgressDialog == null)
            mProgressDialog = VrProgressDialog.Builder(mContext!!).create(null)

        if (loadingTask == null) {
            loadingTask = timerTask {
                val message = Message()
                message.what = 1
                loadingHandler.sendMessage(message)
            }
        }else
            loadingTask?.cancel()

        if (!mProgressDialog?.isShowing!!){
            mProgressDialog?.show()
            loadingTimer.schedule(loadingTask, 500)
        }


    }



    override fun dissLoading() {
        mProgressDialog?.dismiss()
        try {
            loadingTask?.cancel()
            loadingTask = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    protected abstract fun initPresenter() //初始化Presenter
    protected abstract fun initContentView(): Int //加载布局文件
    protected abstract fun initEventAndData() //初始化数据，事件
    protected abstract fun isInitEventBus(): Boolean //是否需要Eventbus 注册

    override fun goLogin() {
        activity?.startActivity(Intent(mContext,LoginActivity::class.java))
    }

    override fun close() {
        activity?.finish()
    }

    @SuppressLint("InflateParams")
    fun getEmptyView(): View {
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty_view, null)
            mEmptyView?.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return mEmptyView!!
    }

}

