package com.duongtung.musicexample.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T> : AppCompatActivity() {
    protected var presenter: T

    init {
        presenter = onPresenterCreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected abstract fun setContentView()
    protected abstract fun onPresenterCreate(): T
}