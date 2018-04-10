package com.mardawang.android.ratingbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,RatingBar.OnRatingBarChangeListener,MineRatingBar.OnRatingChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rb1.setOnRatingBarChangeListener(this)
        rb2.setOnRatingBarChangeListener(this)
        rb3.setOnRatingBarChangeListener(this)

        rb4.setOnRatingChangeListener(this)

    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
        Toast.makeText(this,p1.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onRatingChange(ratingCount: Float) {
        TODO("not implemented")
        Toast.makeText(this,ratingCount.toString(),Toast.LENGTH_SHORT).show()
    }
}
