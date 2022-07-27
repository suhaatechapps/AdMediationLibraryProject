package com.zee.suhaatecs.facebook

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.facebook.ads.*
import com.zee.suhaatecs.R
import com.zee.suhaatecs.databinding.LoadAdsLayoutBinding
import com.zee.suhaatecs.mediation.TrueAdManager
import com.zee.suhaatecs.mediation.TrueError
import com.zee.suhaatecs.mediation.adlimits.TrueAdLimitUtils
import com.zee.suhaatecs.mediation.adlimits.TruePrefUtils
import com.zee.suhaatecs.mediation.callbacks.TrueAdCallbacks
import com.zee.suhaatecs.mediation.callbacks.TrueInterCallbacks
import com.zee.suhaatecs.mediation.database.TrueZSPRepository
import com.zee.suhaatecs.mediation.interfaces.TrueAdCallBackInterface
import com.zee.suhaatecs.mediation.types.TrueAdsType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaceBookAdManager(private var context: Context) {
    private var TAG = "FaceBookAdManagerClass"
    private var hNativeBanner: NativeBannerAd? = null
    private var zFbInterstitialAd: InterstitialAd? = null
    private var hFbBanner: AdView? = null
    private var nativeAd: NativeAd? = null
    private var zInterCallbacks: TrueInterCallbacks? = null
    private var hAdCallbacks: TrueAdCallbacks? = null
    lateinit var dialog: Dialog
    private var prefNameFBInter: String? = null

    fun hGetFbInterstitialAd(): InterstitialAd? {
        return zFbInterstitialAd
    }

    fun loadFbInterstitialAds(
        context: Activity,
        interId: String
    ) {
        if (zFbInterstitialAd != null) {
            zFbInterstitialAd?.destroy()
            zFbInterstitialAd = null
        }
        dialog = Dialog(context)
        loadAds(context)
        Log.d(TAG, "Ads Id: " + interId)
        if (interId.contains("_")) {
            prefNameFBInter = interId.substring(interId.lastIndexOf("_") + 1)
        }
        Log.d(TAG, "Prefer Name Inter: " + prefNameFBInter)
        CoroutineScope(Dispatchers.Main).launch {
            var zCallBackCalled = false
            if (!TrueAdLimitUtils.isBanned(context, prefNameFBInter, "Interstitial Ad")) {
                dialog.show()
                /** it will be executed when its true*/
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        object : InterstitialAdExtendedListener {
                            override fun onInterstitialActivityDestroyed() {}

                            override fun onInterstitialDisplayed(ad: Ad?) {
                                zInterCallbacks?.zOnAddShowed(TrueAdsType.Z_FACEBOOK)
                                Log.d(TAG, "onInterstitialDisplayed: " + ad)
                            }

                            override fun onInterstitialDismissed(ad: Ad?) {
                                zInterCallbacks?.zOnAddDismissed(TrueAdsType.Z_FACEBOOK)
                                Log.d(TAG, "onInterstitialDismissed: " + ad)
                            }

                            override fun onError(ad: Ad?, adError: AdError?) {
                                zInterCallbacks?.zOnAdFailedToLoad(
                                    TrueAdsType.Z_FACEBOOK,
                                    zError = TrueError(
                                        zMessage = adError?.errorMessage,
                                        zCode = adError?.errorCode,
                                    ),
                                )
                                Toast.makeText(
                                    context,
                                    "Error: " + adError!!.errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d(TAG, "onError: " + adError!!.errorMessage)
                            }

                            override fun onAdLoaded(ad: Ad?) {
                                dialog.dismiss()
                                zInterCallbacks?.zOnAddLoaded(TrueAdsType.Z_FACEBOOK)
                                TruePrefUtils.getInstance().init(context, prefNameFBInter)
                                    .zUpdateImpressionCounter()
                                zInterCallbacks?.zOnAddLoaded(zAdType = TrueAdsType.Z_ADMOB)
                                zCallBackCalled = true
                                zFbInterstitialAd!!.show()
                                Log.d(TAG, "onAdLoaded: " + ad)
                            }

                            override fun onAdClicked(ad: Ad?) {
                                Log.d(TAG, "onAdClicked: " + ad)
                                TruePrefUtils.getInstance()
                                    .init(context, prefNameFBInter)
                                    .zUpdateClicksCounter()
                                Log.d(
                                    TAG,
                                    "Click Counter : " + TruePrefUtils.getInstance().clicksCount
                                )
                            }

                            override fun onLoggingImpression(ad: Ad?) {
                                Log.d(TAG, "onLoggingImpression: " + ad)
                            }

                            override fun onRewardedAdCompleted() {}
                            override fun onRewardedAdServerSucceeded() {}
                            override fun onRewardedAdServerFailed() {}
                        }.also { listener ->
                            zFbInterstitialAd = InterstitialAd(
                                context,
                                interId
                            )
                            val loadAdConfig = zFbInterstitialAd!!
                                .buildLoadAdConfig()
                                .withAdListener(listener)
                                .build()
                            zFbInterstitialAd!!.loadAd(loadAdConfig)
                        }
                    }, TruePrefUtils.getInstance().init(context, prefNameFBInter).delayMs
                )
            } else {
                Log.d(
                    TAG, "loadFbInterstitialAds: " +
                            "Inter Ad Is Banned : " + !TrueAdLimitUtils.isBanned(
                        context,
                        prefNameFBInter,
                        "Interstitial Ad"
                    )
                )
            }
        }
    }

    fun zLoadFBInterstitialAdWithCallBacks(
        context: Activity,
        interId: String,
        trueAdCallBackInterface: TrueAdCallBackInterface
    ) {
        dialog = Dialog(context)
        loadAds(context)
        if (interId.contains("_")) {
            prefNameFBInter = interId.substring(interId.lastIndexOf("_") + 1)
        }
        CoroutineScope(Dispatchers.Main).launch {
            var zCallBackCalled = false
            if (!TrueAdLimitUtils.isBanned(context, prefNameFBInter, "Interstitial Ad")) {
                dialog.show()
                /** it will be executed when its true*/
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        object : InterstitialAdExtendedListener {
                            override fun onInterstitialActivityDestroyed() {}

                            override fun onInterstitialDisplayed(ad: Ad?) {
                                zInterCallbacks?.zOnAddShowed(TrueAdsType.Z_FACEBOOK)
                                Log.d(TAG, "onInterstitialDisplayed: " + ad)
                                TrueZSPRepository.setFBAdAvailableValue(
                                    context,
                                    true
                                )
                            }

                            override fun onInterstitialDismissed(ad: Ad?) {

                                zInterCallbacks?.zOnAddDismissed(TrueAdsType.Z_FACEBOOK)
                                trueAdCallBackInterface.onShowAdComplete()
                                TrueZSPRepository.setFBAdAvailableValue(
                                    context,
                                    true
                                )
                                Log.d(TAG, "onInterstitialDismissed: " + ad)
                            }

                            override fun onError(ad: Ad?, adError: AdError?) {
                                TrueZSPRepository.setFBAdAvailableValue(
                                    context,
                                    false
                                )
                                zInterCallbacks?.zOnAdFailedToLoad(
                                    TrueAdsType.Z_FACEBOOK,
                                    zError = TrueError(
                                        zMessage = adError?.errorMessage,
                                        zCode = adError?.errorCode,
                                    ),
                                )
                                dialog.dismiss()
                                Toast.makeText(
                                    context,
                                    "Error: " + adError!!.errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d(TAG, "onError: " + adError!!.errorMessage)
                            }

                            override fun onAdLoaded(ad: Ad?) {
                                TrueZSPRepository.setFBAdAvailableValue(context, true)
                                dialog.dismiss()
                                zInterCallbacks?.zOnAddLoaded(TrueAdsType.Z_FACEBOOK)
                                TruePrefUtils.getInstance().init(context, prefNameFBInter)
                                    .zUpdateImpressionCounter()
                                zInterCallbacks?.zOnAddLoaded(zAdType = TrueAdsType.Z_ADMOB)
                                zCallBackCalled = true
                                zFbInterstitialAd!!.show()
                                Log.d(TAG, "onAdLoaded: " + ad)
                            }

                            override fun onAdClicked(ad: Ad?) {
                                Log.d(TAG, "onAdClicked: " + ad)
                                TruePrefUtils.getInstance()
                                    .init(context, prefNameFBInter)
                                    .zUpdateClicksCounter()
                                Log.d(
                                    TAG,
                                    "Click Counter : " + TruePrefUtils.getInstance().clicksCount
                                )
                            }

                            override fun onLoggingImpression(ad: Ad?) {
                                Log.d(TAG, "onLoggingImpression: " + ad)
                            }

                            override fun onRewardedAdCompleted() {}
                            override fun onRewardedAdServerSucceeded() {}
                            override fun onRewardedAdServerFailed() {}
                        }.also { listener ->
                            zFbInterstitialAd = InterstitialAd(
                                context,
                                interId
                            )
                            val loadAdConfig = zFbInterstitialAd!!
                                .buildLoadAdConfig()
                                .withAdListener(listener)
                                .build()
                            zFbInterstitialAd!!.loadAd(loadAdConfig)
                        }
                    }, TruePrefUtils.getInstance().init(context, prefNameFBInter).delayMs
                )
            } else {
                trueAdCallBackInterface.onShowAdComplete()
                TrueZSPRepository.setFBAdAvailableValue(context, false)
                Log.d(
                    TAG, "loadFbInterstitialAds: " +
                            "Inter Ad Is Banned : " + !TrueAdLimitUtils.isBanned(
                        context,
                        prefNameFBInter,
                        "Interstitial Ad"
                    )
                )
            }
        }

    }

    /**Load Ads Dialogue*/
    private fun loadAds(activity: Activity) {
        val loadAdsLayoutBinding: LoadAdsLayoutBinding =
            LoadAdsLayoutBinding.inflate(activity.layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(loadAdsLayoutBinding.root)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        loadAdsLayoutBinding.tvMessage.text =
            TrueAdManager.context.resources.getString(R.string.loading_ad)
    }

}