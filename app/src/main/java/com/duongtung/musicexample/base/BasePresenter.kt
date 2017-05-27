package com.duongtung.musicexample.base

/**
 * Created by duong on 5/27/2017.
 */

abstract class BasePresenter<T : BaseContact>(receiver: T) {

    protected var receiver: T? = receiver

    fun clear() {
        receiver = null
    }

}