package com.example.adsxml

import android.os.Bundle
import com.example.adsxml.ads.showInterAd
import com.example.adsxml.base.BaseActivity
import com.example.adsxml.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adsManager.initAdsSdk(mContext) {

        }

        interAdsManager.addNewController(
            adKey = "MainInter",
            adId = "ca-app-pub-3940256099942544/1033173712"
        )
        binding.preloadAd.setOnClickListener {
            interAdsManager.getAdController("MainInter")?.loadAd(mContext, null)
        }
        binding.showAd.setOnClickListener {
            showInterAd(
                enable = true,
                context = mContext,
                key = "MainInter",
                interAdsManager
            ) { adShown ->

            }
        }

        showNativeAd(
            "NativeMain",
            "ca-app-pub-3940256099942544/2247696110",
            enabled = true,
            adFrame = binding.adFrame,
            showShimmerLayout = true
        )
    }
}