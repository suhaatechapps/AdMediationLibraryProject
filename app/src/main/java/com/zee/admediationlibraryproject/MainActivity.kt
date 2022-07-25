package com.zee.admediationlibraryproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.zee.admediationlibraryproject.databinding.ActivityMainBinding
import com.zee.suhaatecs.mediation.TrueAdManager
import com.zee.suhaatecs.mediation.TrueAdMobManager
import com.zee.suhaatecs.mediation.TrueConstants
import com.zee.suhaatecs.mediation.database.TrueZSPRepository
import com.zee.suhaatecs.mediation.interfaces.TrueAdCallBackInterface

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var zAdMobManager: TrueAdMobManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zAdMobManager = TrueAdMobManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            if (TrueConstants.isNetworkAvailable(this) && TrueConstants.isNetworkSpeedHigh()) {
                binding.splashProgress.visibility = View.GONE
                TrueAdManager.zShowInterstitial(
                    this,
                    "ca-app-pub-3940256099942544/1033173712",
                    object : TrueAdCallBackInterface {
                        override fun onShowAdComplete() {
                            if (TrueZSPRepository.getIfAdAvailable(this@MainActivity)) {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        MainActivity::class.java
                                    )
                                )
                            } else {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        MainActivity::class.java
                                    )
                                )
                            }
                        }
                    })
            } else {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }
        }, 4000)

        TrueAdManager.zLoadInterstitialInAdvance(
            this,
            "ca-app-pub-3940256099942544/1033173712"
        )

        TrueAdManager.zLoadSimpleNativeAdInAdvance(
            this,
            "ca-app-pub-3940256099942544/2247696110"
        )

    }
}