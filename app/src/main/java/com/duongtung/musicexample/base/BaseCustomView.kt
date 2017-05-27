package com.duongtung.musicexample.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout

/**
 * Created by duong on 5/27/2017.
 */
abstract class BaseCustomView<T> : RelativeLayout {

    protected var presenter: T

    init {
        presenter = onPresenterCreate()
    }

    constructor(context: Context) : super(context) {
        initialize(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context)
    }

    private fun initialize(context: Context) {
        val inflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(getLayoutId(), this)
        afterViews()
    }

    protected abstract fun afterViews()

    protected abstract fun onPresenterCreate(): T

    protected abstract fun getLayoutId(): Int

}