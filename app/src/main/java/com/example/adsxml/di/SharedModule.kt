package com.example.adsxml.di

import com.example.core.IshfaqAdsManager
import com.example.native_ads.NativeAdsManager
import org.koin.dsl.module

val SharedModule = module {
    single {
        NativeAdsManager()
    }
    single {
        IshfaqAdsManager()
    }
}