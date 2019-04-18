package com.kanfang123.vrhouse.kanfang.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kanfang123.vrhouse.kanfang.R

/**
 * description ：加载圈
 * created by simon : 2019/4/17
 */
class VrProgressDialog : Dialog{

    constructor(context: Context,theme: Int) : super(context,theme)

    constructor(context: Context) : super(context)

    class Builder(val context: Context) {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonText: String? = null
        private var negativeButtonText: String? = null
        private var contentView: View? = null

        private var positiveButtonClickListener: DialogInterface.OnClickListener? = null
        private var negativeButtonClickListener: DialogInterface.OnClickListener? = null


        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }


        fun setMessage(message: Int): Builder {
            this.message = context.getText(message) as String
            return this
        }


        fun setTitle(title: Int): Builder {
            this.title = context.getText(title) as String
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setContentView(v: View): Builder {
            this.contentView = v
            return this
        }


        fun setPositiveButton(positiveButtonText: Int,
                              listener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonText = context
                    .getText(positiveButtonText) as String
            this.positiveButtonClickListener = listener
            return this
        }

        fun setPositiveButton(positiveButtonText: String,
                              listener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonText = positiveButtonText
            this.positiveButtonClickListener = listener
            return this
        }

        fun setNegativeButton(negativeButtonText: Int,
                              listener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonText = context
                    .getText(negativeButtonText) as String
            this.negativeButtonClickListener = listener
            return this
        }

        fun setNegativeButton(negativeButtonText: String,
                              listener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonText = negativeButtonText
            this.negativeButtonClickListener = listener
            return this
        }

        fun create(loading: String?): VrProgressDialog {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialog = VrProgressDialog(context, R.style.ProgressDialog)
            val layout = inflater.inflate(R.layout.layout_progress_dialog, null)
            dialog.addContentView(layout, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))

            val mLoadingIV = layout.findViewById(R.id.loading_iv) as ImageView
            if (loading != null)
                (layout.findViewById(R.id.loading_tv) as TextView).text = loading
            val operatingAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_loading)
            val lin = LinearInterpolator()
            operatingAnim.interpolator = lin
            mLoadingIV.startAnimation(operatingAnim)
            dialog.setContentView(layout)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }

    }
}