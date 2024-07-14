package com.example.native_ads.base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.IshfaqAdsManager
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.native_ads.NativeAdsManager
import com.example.native_ads.R
import com.example.native_ads.ui.IshfaqNative
import org.koin.android.ext.android.inject

abstract class BaseNativeAdsActivity : AppCompatActivity() {


    val adsManager: IshfaqAdsManager by inject()
    val nativeAdsManager: NativeAdsManager by inject()
    private var nativeAdController: AdsController? = null
    private var nativeAd: AdUnit? = null

    private var isShowNativeAdCalled = false
    private var key = ""
    private var adId = ""
    private var layoutName = ""
    private var enabled = false
    private var adFrame: FrameLayout? = null
    private var adLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showNativeAd(
        key: String,
        adId: String,
        layoutName: String = "native_ad_normal_layout",
        enabled: Boolean,
        adFrame: FrameLayout
    ) {
        this.key = key
        this.adId = adId
        this.layoutName = layoutName
        this.enabled = enabled
        this.adLoaded = false
        this.adFrame = adFrame
        isShowNativeAdCalled = true
        loadAd()
    }

    override fun onResume() {
        super.onResume()
        if (isShowNativeAdCalled) {
            loadAd()
        }
    }

    private fun loadAd() {
        if (adLoaded && nativeAd != null) {
            populateNativeAd()
            return
        }
        if (enabled.not()) {
            return
        }
        nativeAdsManager.addNewController(key, adId)
        nativeAdController = nativeAdsManager.getAdController(key)
        nativeAdController?.loadAd(
            (this@BaseNativeAdsActivity), object : AdsLoadingStatusListener {
                override fun onAdLoaded() {
                    nativeAd = nativeAdController?.getAvailableAd()
                    populateNativeAd()
                }

                override fun onAdFailedToLoad(message: String, code: Int) {

                }
            }
        )
    }


    override fun onPause() {
        super.onPause()
        nativeAdController?.resetListener(this@BaseNativeAdsActivity)
    }


    private fun populateNativeAd() {
        nativeAd?.let {
            layoutName.inflateLayoutByName(this@BaseNativeAdsActivity)?.let { layout ->
                adFrame?.removeAllViews()
                adFrame?.addView(layout)
                layout.findViewById<IshfaqNative>(R.id.ishfaqNative)?.let { view ->
                    nativeAdsManager.populateAd(view, it)
                }
            }
        }

    }
}