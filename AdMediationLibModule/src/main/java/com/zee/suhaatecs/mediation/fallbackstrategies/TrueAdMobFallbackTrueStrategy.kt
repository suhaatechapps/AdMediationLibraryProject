package com.zee.suhaatecs.mediation.fallbackstrategies

import com.zee.suhaatecs.mediation.types.TrueAdPriorityType
import com.zee.suhaatecs.mediation.types.TrueAdsType

object TrueAdMobFallbackTrueStrategy : TrueStrategy {

    override fun zBannerStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType
    ): TrueAdPriorityType {
        return when (zGlobalPriority) {
            TrueAdPriorityType.Z_AD_MOB -> {
                when (zAdsType) {
                    TrueAdsType.Z_ADMOB -> TrueAdPriorityType.Z_NONE
                    else -> TrueAdPriorityType.Z_NONE
                }
            }
            else -> TrueAdPriorityType.Z_NONE
        }
    }

    override fun zNativeBannerStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType
    ): TrueAdPriorityType {
        return when (zGlobalPriority) {
            TrueAdPriorityType.Z_AD_MOB -> {
                when (zAdsType) {
                    TrueAdsType.Z_ADMOB -> TrueAdPriorityType.Z_NONE
                    else -> TrueAdPriorityType.Z_NONE
                }
            }
            else -> TrueAdPriorityType.Z_NONE
        }
    }

    override fun zNativeAdvancedStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType
    ): TrueAdPriorityType {
        return when (zGlobalPriority) {
            TrueAdPriorityType.Z_AD_MOB -> {
                when (zAdsType) {
                    TrueAdsType.Z_ADMOB -> TrueAdPriorityType.Z_NONE
                    else -> TrueAdPriorityType.Z_NONE
                }
            }
            else -> TrueAdPriorityType.Z_NONE
        }
    }

    override fun zInterstitialStrategy(
        zGlobalPriority: TrueAdPriorityType,
        zAdsType: TrueAdsType
    ): TrueAdPriorityType {
        return when (zGlobalPriority) {
            TrueAdPriorityType.Z_AD_MOB -> {
                when (zAdsType) {
                    TrueAdsType.Z_ADMOB -> TrueAdPriorityType.Z_NONE
                    else -> TrueAdPriorityType.Z_NONE
                }
            }
            else -> TrueAdPriorityType.Z_NONE
        }
    }
}