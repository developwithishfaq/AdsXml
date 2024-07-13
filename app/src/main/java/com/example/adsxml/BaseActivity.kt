package com.example.adsxml

import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.IshfaqAdsManager
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.native_ads.NativeAdsManager
import com.example.native_ads.ui.IshfaqNative
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Activity

    val adsManager: IshfaqAdsManager by inject()
    val nativeAdsManager: NativeAdsManager by inject()
    private var nativeAdController: AdsController? = null
    private var nativeAd: AdUnit? = null

    private var isShowNativeAdCalled = false
    private var key = ""
    private var layoutName = ""
    private var enabled = false
    private var adFrame: FrameLayout? = null
    private var adLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    fun showNativeAd(
        key: String,
        layoutName: String = "native_ad_normal_layout",
        enabled: Boolean,
        adFrame: FrameLayout
    ) {
        this.key = key
        this.layoutName = layoutName
        this.enabled = enabled
        this.adLoaded = false
        this.adFrame = adFrame
        isShowNativeAdCalled = true
        loadAd()
    }

    private fun loadAd() {
        if (adLoaded && nativeAd != null) {
            populateNativeAd()
            return
        }
        if (enabled.not()) {
            return
        }
        nativeAdsManager.addNewController(key, "ca-app-pub-3940256099942544/2247696110")
        nativeAdController = nativeAdsManager.getAdController(key)
        nativeAdController?.loadAd(
            mContext, object : AdsLoadingStatusListener {
                override fun onAdLoaded() {
                    nativeAd = nativeAdController?.getAvailableAd()
                    populateNativeAd()
                }

                override fun onAdFailedToLoad(message: String, code: Int) {

                }
            }
        )
    }


    private fun populateNativeAd() {
        nativeAd?.let {
            layoutName.inflateLayoutByName(mContext)?.let { layout ->
                adFrame?.removeAllViews()
                adFrame?.addView(layout)
                layout.findViewById<IshfaqNative>(R.id.ishfaqNative)?.let { view ->
                    nativeAdsManager.populateAd(mContext, adFrame!!, view, it)
                }
            }
        }

    }
}