package com.example.adsxml.base

import android.app.Activity
import android.os.Bundle
import com.example.native_ads.base.BaseNativeAdsActivity

abstract class BaseActivity : BaseNativeAdsActivity() {
    lateinit var mContext: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}