package com.example.native_ads

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.core.ad_units.IshfaqNativeAd
import com.example.core.ad_units.core.AdType
import com.example.core.ad_units.core.AdUnit
import com.example.core.commons.NativeConstants.makeGone
import com.example.native_ads.ui.IshfaqNativeView
import com.example.native_ads.ui.IshfaqNativeMediaView
import com.google.android.gms.ads.nativead.NativeAd

class AdMobNativeAd(val nativeAd: NativeAd) : IshfaqNativeAd {
    override fun getTitle(): String? {
        return nativeAd.headline
    }

    override fun getDescription(): String? {
        return nativeAd.body
    }

    override fun getCtaText(): String? {
        return nativeAd.callToAction
    }

    override fun getAdvertiserName(): String? {
        return nativeAd.advertiser
    }

    override fun destroyAd() {

    }


    override fun getAdType(): AdType {
        return AdType.NATIVE
    }
}