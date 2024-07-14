package com.example.native_ads.base

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.core.AdsController
import com.example.core.AdsLoadingStatusListener
import com.example.core.IshfaqAdsSdk
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.AdsCommons.logAds
import com.example.core.commons.NativeConstants.inflateLayoutByName
import com.example.native_ads.NativeAdsManager
import com.example.native_ads.R
import com.example.native_ads.ui.IshfaqNativeView
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject

abstract class BaseNativeAdsActivity : AppCompatActivity() {


    val adsManager: IshfaqAdsSdk by inject()
    private val nativeAdsManager: NativeAdsManager by inject()
    private var nativeAdController: AdsController? = null
    private var nativeAd: AdUnit? = null

    private var isShowNativeAdCalled = false
    private var key = ""
    private var adId = ""
    private var layoutName = ""
    private var enabled = false
    private var adFrame: FrameLayout? = null
    private var adLoaded = false
    private var oneTimeUse = false
    private var showShimmerLayout = false


    fun showNativeAd(
        key: String,
        adId: String,
        layoutName: String = "native_ad_normal_layout",
        enabled: Boolean,
        adFrame: FrameLayout,
        showShimmerLayout: Boolean = true,
        oneTimeUse: Boolean = true
    ) {
        this.key = key
        this.adId = adId
        this.oneTimeUse = oneTimeUse
        this.layoutName = layoutName
        this.enabled = enabled
        this.showShimmerLayout = showShimmerLayout
        this.adLoaded = false
        this.adFrame = adFrame
        isShowNativeAdCalled = true
        loadAd()
    }

    override fun onResume() {
        super.onResume()
        if (isShowNativeAdCalled && enabled) {
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
        if (showShimmerLayout) {
            showShimmerLayout()
        }
        nativeAdsManager.addNewController(key, adId)
        nativeAdController = nativeAdsManager.getAdController(key)
        nativeAdController?.loadAd(
            (this@BaseNativeAdsActivity), object : AdsLoadingStatusListener {
                override fun onAdLoaded() {
                    adLoaded = true
                    nativeAd = nativeAdController?.getAvailableAd()

                    populateNativeAd()
                }

                override fun onAdFailedToLoad(message: String, code: Int) {

                }
            }
        )
    }

    private fun showShimmerLayout() {
        val shimmerLayout =
            LayoutInflater.from(this@BaseNativeAdsActivity).inflate(R.layout.shimmer, null, false)

        val adLayout = layoutName.inflateLayoutByName(this@BaseNativeAdsActivity)

        shimmerLayout?.findViewById<ShimmerFrameLayout>(R.id.shimmerRoot)?.let { shimmer ->
            shimmer.removeAllViews()
            shimmer.addView(adLayout)
            adFrame?.removeAllViews()
            adFrame?.addView(shimmer)
        }

    }


    override fun onPause() {
        super.onPause()
        nativeAdController?.resetListener(this@BaseNativeAdsActivity)
    }


    private fun populateNativeAd() {
        logAds("populateNativeAd Called")
        nativeAd?.let {
            layoutName.inflateLayoutByName(this@BaseNativeAdsActivity)?.let { layout ->
                adFrame?.removeAllViews()
                adFrame?.addView(layout)
                layout.findViewById<IshfaqNativeView>(R.id.ishfaqNative)?.let { view ->
                    (nativeAdsManager as? NativeAdsManager)?.populateAd(view, it) {
                        if (oneTimeUse) {
                            destroyLoadedAd()
                        }
                    }
                }
            }
        }
    }

    fun destroyLoadedAd() {
        nativeAdController?.destroyAd(this@BaseNativeAdsActivity)
    }
}