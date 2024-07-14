package com.example.adsxml.base

import android.app.Activity
import android.os.Bundle
import com.example.core.AdsManager
import com.example.inter.InterstitialAdsManager
import com.example.native_ads.base.BaseNativeAdsActivity
import org.koin.android.ext.android.inject

abstract class BaseActivity : BaseNativeAdsActivity() {
    lateinit var mContext: Activity

    val interAdsManager : InterstitialAdsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}