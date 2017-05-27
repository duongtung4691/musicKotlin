package com.duongtung.musicexample.base

import android.content.Context
import android.support.v4.content.AsyncTaskLoader


/**
 * Created by duong on 5/27/2017.
 */
abstract class BaseAsynTaskLoader<T>(context: Context) : AsyncTaskLoader<T>(context) {
    protected var mData: T? = null

    override fun deliverResult(data: T?) {
        if (isReset) {
            if (data != null) {
                onReleaseResources(data)
            }
        }
        val oldData = mData
        mData = data

        if (isStarted) {
            super.deliverResult(data)
        }

        if (oldData != null) {
            onReleaseResources(oldData)
        }
    }

    /**
     * Handles a request to start the Loader.
     */
    override fun onStartLoading() {
        if (mData != null) {
            deliverResult(mData)
        }

        if (takeContentChanged() || mData == null) {
            forceLoad()
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    override fun onStopLoading() {
        cancelLoad()
    }

    /**
     * Handles a request to cancel a load.
     */
    override fun onCanceled(data: T?) {
        super.onCanceled(data)

        onReleaseResources(data)
    }

    override fun onReset() {
        super.onReset()

        onStopLoading()

        if (mData != null) {
            onReleaseResources(mData)
            mData = null
        }
    }

    protected fun onReleaseResources(data: T?) {
    }
}