package com.example.adsxml

import android.os.Bundle
import com.example.core.ad_units.core.AdUnit

abstract class BaseNativeAdsActivity : BaseActivity() {

    private val nativeAd: AdUnit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}