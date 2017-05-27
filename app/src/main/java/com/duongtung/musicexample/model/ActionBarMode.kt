package com.duongtung.musicexample.model

import android.support.v7.widget.DialogTitle

/**
 * Created by duong on 5/28/2017.
 */
class ActionBarMode {
    var title: String? = null
    var titleLeft: String? = null
    var titleRight: String? = null

    constructor(title: String?, titleLeft: String?, titleRight: String?) {
        this.title = title
        this.titleLeft = titleLeft
        this.titleRight = titleRight
    }
}