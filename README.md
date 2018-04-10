# RatingbarDemo

RatingBar是基于SeekBar（拖动条）和ProgressBar（状态条）的扩展，用星形来显示等级评定！

该项目就是一个使用Kotlin语言编写的针对ratingbar做了改造的项目(主要包含四种方式：两种系统默认方式，一种自定义style样式，一种重写ratingbar样式);

## 背景
#### 1.由于开发过程中，遇到了需要对客户评分并展示的需求，由于需求较为复杂，不适用于直接使用系统自带的ratingbar，所以只好自定义。

#### 2.之前不曾遇到过这样的需求，也没有操作过。

#### 3.在项目中使用Kotlin编码较少，这次纯粹当作练手。


下面是效果图，
![](001.png)

下面是部分核心代码

<RatingBar
        android:id="@+id/rb3"
        style="@style/mine_ratingbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:rating="1.2"
        />

<com.mardawang.android.ratingbardemo.MineRatingBar
        android:id="@+id/rb4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        app:starPadding="10dp"
        app:starImageSize="28dp"
        app:starCount="5"
        app:starEmpty="@drawable/icon_ratingbar_off"
        app:starFill="@drawable/icon_ratingbar_on"/>


-------------------------------------------------------

init {
        orientation = LinearLayout.HORIZONTAL
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar)
        starImageSize = mTypedArray.getDimension(R.styleable.RatingBar_starImageSize, 20f)
        starPadding = mTypedArray.getDimension(R.styleable.RatingBar_starPadding, 10f)
        starStep = mTypedArray.getFloat(R.styleable.RatingBar_starStep, 1.0f)
        stepSize = StepSize.fromStep(mTypedArray.getInt(R.styleable.RatingBar_stepSize, 1))
        starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5)
        starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty)
        starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starFill)
        starHalfDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starHalf)
        mClickable = mTypedArray.getBoolean(R.styleable.RatingBar_clickable, true)
        mTypedArray.recycle()
        for (i in 0 until starCount) {
            val imageView = starImageView
            imageView.setImageDrawable(starEmptyDrawable)
            imageView.setOnClickListener(
                    OnClickListener { v ->
                        if (mClickable) {
                            //浮点数的整数部分
                            var fint = starStep.toInt()
                            val b1 = BigDecimal(java.lang.Float.toString(starStep))
                            val b2 = BigDecimal(Integer.toString(fint))
                            //浮点数的小数部分
                            val fPoint = b1.subtract(b2).toFloat()
                            if (fPoint == 0f) {
                                fint -= 1
                            }

                            if (indexOfChild(v) > fint) {
                                setStar((indexOfChild(v) + 1).toFloat())
                            } else if (indexOfChild(v) == fint) {
                                if (stepSize == StepSize.Full) {//如果是满星 就不考虑半颗星了
                                    return@OnClickListener
                                }
                                //点击之后默认每次先增加一颗星，再次点击变为半颗星
                                if (imageView.drawable.current.constantState == starHalfDrawable!!.constantState) {
                                    setStar((indexOfChild(v) + 1).toFloat())
                                } else {
                                    setStar(indexOfChild(v) + 0.5f)
                                }
                            } else {
                                setStar(indexOfChild(v) + 1f)
                            }

                        }
                    }
            )
            addView(imageView)
        }
        setStar(starStep)
    }


可以看到在我的model层并没有做太多操作，只是对ratingbar的简单监听。


如果对你有那么一丝的帮助，请不要吝啬。求star！求star！求star！求star！求star！

If you like this library's design, feel it help to you, you can point the upper right corner "Star" support Thank you! ^ _ ^


