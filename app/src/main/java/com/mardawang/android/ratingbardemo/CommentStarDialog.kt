package com.mardawang.android.ratingbardemo

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView

/**
 * author mardawang
 *
 *
 * email:wy363681759@163.com
 *
 *
 * date: 2018/4/10
 *
 *
 * desc:
 */

class CommentStarDialog : Dialog, View.OnClickListener,MineRatingBar.OnRatingChangeListener {

    internal lateinit var mcontext: Context
    private var mRb_comment: MineRatingBar? = null
    private var mTv_cancel: TextView? = null
    private var mTv_confirm: TextView? = null
    private lateinit var mListener: OnItemListener
    private var mCurStar: Float = 0f

    constructor(context: Context, listener: OnItemListener) : super(context) {
        mcontext = context
        mListener = listener
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_comment_ratingbar)
        initView()
    }

    private fun initView() {
        mRb_comment = findViewById<View>(R.id.rb_comment) as MineRatingBar
        mTv_cancel = findViewById<View>(R.id.tv_cancel) as TextView
        mTv_confirm = findViewById<View>(R.id.tv_confirm) as TextView

        mRb_comment!!.setOnRatingChangeListener(this)
        mTv_cancel!!.setOnClickListener(this)
        mTv_confirm!!.setOnClickListener(this)

        //设置dialog大小
        val dialogWindow = window
        val manager = (mcontext as Activity).windowManager
        val params = dialogWindow!!.attributes
        // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER)
        val d = manager.defaultDisplay
        // 获取屏幕宽、高度
        params.width = (d.width * 0.88).toInt()
        dialogWindow.attributes = params

    }

    override fun onRatingChange(ratingCount: Float) {
        mCurStar = ratingCount
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.tv_cancel) {

        } else if (i == R.id.tv_confirm) {
            mListener.onItemCheck(mCurStar)
        }
        dismiss()
    }

    interface OnItemListener {
        fun onItemCheck(s: Float)
    }
}
