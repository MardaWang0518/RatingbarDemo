package com.mardawang.android.ratingbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener,RatingBar.OnRatingBarChangeListener,MineRatingBar.OnRatingChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rb1.setOnRatingBarChangeListener(this)
        rb2.setOnRatingBarChangeListener(this)
        rb3.setOnRatingBarChangeListener(this)

        rb4.setOnRatingChangeListener(this)

        tv_comment.setOnClickListener(this)

    }

    var listener: CommentStarDialog.OnItemListener = object : CommentStarDialog.OnItemListener {
        override fun onItemCheck(s: Float) {
            showToast(s.toString())
        }
    }

    override fun onRatingChanged(p0: RatingBar?, ratingCount: Float, p2: Boolean) {
        showToast(ratingCount.toString())
    }

    override fun onRatingChange(ratingCount: Float) {
        showToast(ratingCount.toString())

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_comment -> showDialog()
            }
    }

    private fun showDialog() {
        var mCommentDialog = CommentStarDialog(this, listener)

        mCommentDialog.show()
    }

    private fun showToast(str: String) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }
}
