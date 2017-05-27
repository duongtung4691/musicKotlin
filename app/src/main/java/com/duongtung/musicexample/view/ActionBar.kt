package com.duongtung.musicexample.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.duongtung.musicexample.R
import com.duongtung.musicexample.databinding.ToolbarBinding
import com.duongtung.musicexample.model.ActionBarMode


/**
 * Created by duong on 5/27/2017.
 */
class ActionBar : RelativeLayout {
    var actionbarBinding: ToolbarBinding? = null
    var title: String? = null
    var leftTitle: String? = null
    var rightTitle: String? = null
    var leftImageButton: Int? = null
    var rightImageButton: Int? = null
    var actionbarMode: ActionBarMode? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttr(attrs)
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttr(attrs)
        init(context)
    }

    fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        actionbarBinding = DataBindingUtil.inflate(inflater, R.layout.toolbar,
                this, true)
        actionbarBinding?.actionbar = actionbarMode
    }


    private fun getAttr(attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ActionBar, 0, 0)
        try {
            title = array.getString(R.styleable.ActionBar_title)
            leftTitle = array.getString(R.styleable.ActionBar_leftTitleButton)
            rightTitle = array.getString(R.styleable.ActionBar_rightTitleButton)
            leftImageButton = array.getInt(R.styleable.ActionBar_imageLeftButton, -1)
            rightImageButton = array.getInt(R.styleable.ActionBar_imageRightButton, -1)
            actionbarMode = ActionBarMode(title, leftTitle, rightTitle)
        } finally {
            array?.recycle()
        }
    }

}