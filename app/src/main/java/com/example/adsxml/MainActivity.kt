package com.example.adsxml

import android.os.Bundle
import com.example.adsxml.databinding.ActivityMainBinding

class MainActivity : BaseNativeAdsActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adsManager.initAdsSdk(mContext) {

        }
        showNativeAd(
            "NativeMain",
            enabled = true,
            adFrame = binding.adFrame
        )

    }
}