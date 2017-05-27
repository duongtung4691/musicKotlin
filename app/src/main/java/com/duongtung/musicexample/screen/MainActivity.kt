package com.duongtung.musicexample.screen

import android.databinding.DataBindingUtil
import com.duongtung.musicexample.R
import com.duongtung.musicexample.base.BaseActivity
import com.duongtung.musicexample.contact.MainAcitivyContact
import com.duongtung.musicexample.databinding.ActivityMainBinding
import com.duongtung.musicexample.presenter.MainActivityPresenter

class MainActivity : BaseActivity<MainActivityPresenter>(), MainAcitivyContact {

    private var mainBinding: ActivityMainBinding? = null

    override fun setContentView() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun loadMusic() {

    }


    override fun onPresenterCreate(): MainActivityPresenter {
        return MainActivityPresenter(this)
    }


}
