package com.zee.suhaatecs.mediation.callbacks

import android.app.Activity
import com.zee.suhaatecs.mediation.TrueError
import com.zee.suhaatecs.mediation.types.TrueAdsType

abstract class TrueInterCallbacks() {

    open fun zOnAdFailedToLoad(
        zAdType: TrueAdsType,
        zError: TrueError,
        zActivity: Activity? = null
    ) {

    }

    open fun zOnAddLoaded(
        zAdType: TrueAdsType
    ) {
    }

    open fun zOnAdFailedToShowFullContent(
        zAdType: TrueAdsType,
        zError: TrueError
    ) {
    }

    open fun zOnAddShowed(
        zAdType: TrueAdsType
    ) {
    }

    open fun zOnAddDismissed(
        zAdType: TrueAdsType
    ) {
    }

    open fun zOnAdTimedOut(
        zAdType: TrueAdsType
    ) {

    }
}