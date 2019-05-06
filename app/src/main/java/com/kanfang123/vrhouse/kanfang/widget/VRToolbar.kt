package com.kanfang123.vrhouse.kanfang.widget

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.kanfang123.vrhouse.kanfang.R

/**
 * description ：
 * created by simon : 2019/4/24
 */
class VRToolbar : LinearLayout {
    private var mContext: Context
    private lateinit var mTextView: TextView
    private lateinit var mBack: ImageButton
    private lateinit var mImage: ImageButton
    var mListener: OnClickedListener? = null

    constructor(context: Context) : super(context){
        this.mContext = context
    }

    constructor(context: Context,attrs: AttributeSet?) : super(context,attrs) {
        this.mContext = context
        initConext(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr) {
        this.mContext = context
        initConext(attrs)
    }

    private fun initConext(attrs: AttributeSet?) {
        LayoutInflater.from(mContext).inflate(R.layout.layout_toolbar,this,true)
        val attr: TypedArray = mContext.obtainStyledAttributes(attrs,R.styleable.VRToolbar,0,0)
        mTextView = findViewById(R.id.title_tv)
        mBack = findViewById(R.id.btn_back)
        mImage = findViewById(R.id.btn_image)
        val isBack = attr.getBoolean(R.styleable.VRToolbar_backEnabled,true)
        val isMode = attr.getBoolean(R.styleable.VRToolbar_modeEnabled,false)
        val title = attr.getString(R.styleable.VRToolbar_title)
        attr.recycle()
        mTextView.text = title
        if (isBack) {
            mBack.visibility = View.VISIBLE
            mBack.setOnClickListener{
                val activity = mContext as Activity
                activity.finish()
            }
        }

        if (isMode) {
            mImage.visibility = View.VISIBLE
            mImage.setOnClickListener {
                mListener!!.onClicked(it)
            }
        }
    }

    interface OnClickedListener{
        fun onClicked(view: View)
    }

    fun setImageClickedListener(listener: OnClickedListener) {
        mListener = listener
    }
}