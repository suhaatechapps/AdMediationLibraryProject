package com.zee.suhaatecs.mediation.fallbackstrategies

import com.zee.suhaatecs.mediation.types.TrueAdPriorityType
import com.zee.suhaatecs.mediation.types.TrueAdsType

interface TrueStrategy {
    fun zBannerStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType,
    ): TrueAdPriorityType

    fun zNativeAdvancedStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType,
    ): TrueAdPriorityType

    fun zInterstitialStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType,
    ): TrueAdPriorityType

    fun zNativeBannerStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType,
    ): TrueAdPriorityType
}