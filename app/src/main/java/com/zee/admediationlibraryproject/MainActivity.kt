package com.zee.admediationlibraryproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.zee.admediationlibraryproject.databinding.ActivityMainBinding
import com.zee.suhaatecs.facebook.FaceBookAdManager
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        zAdMobManager = TrueAdMobManager(this)
        binding.showAds.setOnClickListener {
            TrueAdManager.zShowFbInterstitial(this, "IMG_16_9_APP_INSTALL#418698876951921_418702163618259")
        }


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