package com.example.native_ads

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.core.AdsController
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.makeGone
import com.example.native_ads.ui.IshfaqNative

class NativeAdsManager(
) {

    private val adsMap = HashMap<String, NativeAdsController>()

    fun getAdController(key: String): AdsController? = adsMap[key]

    fun addNewController(
        adKey: String, adId: String
    ) {
        val controller = adsMap[adKey]
        if (controller == null) {
            adsMap[adKey] = NativeAdsController(adId, adId)
        }
    }

    fun populateAd(context: Context,adFrame: View, adViewLayout: IshfaqNative?, ad: AdUnit) {
        (ad as AdMobNativeAd).nativeAd?.let { nativeAd ->
            adViewLayout?.apply {
                getNativeAdView()?.let { nativeAdView ->
                    val adIcon: ImageView? = findViewById(R.id.adIcon)
                    val adHeadLine: TextView? = findViewById(R.id.adHeadline)
                    val adBody: TextView? = findViewById(R.id.adBody)
                    val adCtaBtn: TextView? = findViewById(R.id.adCtaBtn)
//                    val mMedia = findViewById<MediaView>(R.id.mediaView)
                    /*
                                        nativeAdView.mediaView = mMedia
                                        try {
                                            nativeAdView.mediaView?.let { adMedia ->
                                                adMedia.makeGone(nativeAd.mediaContent == null)
                                                mMedia.makeGone(nativeAd.mediaContent == null)
                                                if (nativeAd.mediaContent != null) {
                                                    adMedia.mediaContent = nativeAd.mediaContent
                                                }
                                            } ?: run {
                                                nativeAdView.mediaView?.makeGone()
                                                mMedia?.makeGone()
                                            }
                                        } catch (_: Exception) {
                                            nativeAdView.mediaView?.makeGone()
                                            mMedia?.makeGone()
                                        }*/
                    val mIconView = nativeAdView.findViewById<ImageView>(R.id.adIcon)
                    nativeAdView.iconView = mIconView
                    nativeAdView.iconView?.let {
                        nativeAd.icon.let { icon ->
                            nativeAdView.mediaView?.makeGone(icon == null)
                            if (icon != null) {
                                (it as ImageView).setImageDrawable(icon.drawable)
                            }
                        }
                    } ?: run {
                        mIconView.makeGone()
                    }

                    nativeAdView.callToActionView = adCtaBtn
                    nativeAdView.bodyView = adBody
                    nativeAdView.iconView = adIcon
                    nativeAdView.headlineView = adHeadLine

                    if (nativeAd.headline.isNullOrEmpty()) {
                        adHeadLine?.visibility = View.GONE
                    } else {
                        adHeadLine?.visibility = View.VISIBLE
                        adHeadLine?.text = nativeAd.headline
                    }

                    if (nativeAd.body.isNullOrEmpty()) {
                        adBody?.visibility = View.GONE
                    } else {
                        adBody?.visibility = View.VISIBLE
                        adBody?.text = nativeAd.body
                    }
                    if (nativeAd.icon == null) {
                        adIcon?.visibility = View.GONE
                    } else {
                        adIcon?.setImageDrawable(nativeAd.icon!!.drawable)
                        adIcon?.visibility = View.VISIBLE
                    }
                    nativeAd.callToAction?.let { btn ->
                        adCtaBtn?.text = btn
                    }
                    nativeAdView.setNativeAd(nativeAd)

                }
            }
        }
    }

}