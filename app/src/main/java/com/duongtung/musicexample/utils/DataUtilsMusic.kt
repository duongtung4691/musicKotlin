package com.duongtung.musicexample.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.load.engine.DiskCacheStrategy
import android.text.TextUtils
import com.bumptech.glide.signature.StringSignature
import android.support.v4.view.ViewCompat.animate
import com.bumptech.glide.DrawableRequestBuilder
import android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import android.system.Os.link
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop


/**
 * Created by duong on 5/28/2017.
 */
class DataUtilsMusic {
    @BindingAdapter(value = *arrayOf("bind:imageUrl", "bind:placeholder", "bind:error"), requireAll = false)
    fun setImageUrl(view: ImageView, url: String, placeholder: Drawable, error: Drawable) {
        val context = view.context

        if (context is Activity && context.isFinishing) return
        if (context is ContextWrapper) {
            val baseContext = context.baseContext
            if (baseContext is Activity && baseContext.isFinishing) return
        }
        val isEmptyPath = TextUtils.isEmpty(url)
        if (isEmptyPath) {
            if (placeholder != null) {
                view.setImageDrawable(placeholder)
            }
            return
        }
        try {
            val request: RequestManager = Glide.with(context)
            request.load(url)
                    .asBitmap()
                    .centerCrop()
                    .error(error)
                    .placeholder(placeholder)
                    .into(object : BitmapImageViewTarget(view) {
                        override fun setResource(resource: Bitmap) {
                            val circularBitmapDrawable = RoundedBitmapDrawableFactory
                                    .create(view.context.resources, resource)
                            circularBitmapDrawable.isCircular = true
                            view.setImageDrawable(circularBitmapDrawable)
                        }
                    })
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

}