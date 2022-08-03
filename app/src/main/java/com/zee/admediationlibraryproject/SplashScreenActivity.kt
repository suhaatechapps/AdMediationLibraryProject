package com.zee.admediationlibraryproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.zee.admediationlibraryproject.databinding.ActivitySplashScreenBinding
import com.zee.suhaatecs.mediation.TrueAdManager
import com.zee.suhaatecs.mediation.TrueConstants
import com.zee.suhaatecs.mediation.database.TrueZSPRepository
import com.zee.suhaatecs.mediation.interfaces.TrueAdCallBackInterface

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashProgress.visibility = View.VISIBLE
        binding.splashStartBtn.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashProgress.visibility = View.GONE
            binding.splashStartBtn.visibility = View.VISIBLE
        }, 4000)
        TrueAdManager.zLoadInterstitialInAdvance(this, "ca-app-pub-3940256099942544/1033173712")
        binding.splashStartBtn.setOnClickListener {
            if (TrueConstants.isNetworkAvailable(this@SplashScreenActivity) && TrueConstants.isNetworkSpeedHigh()) {
                TrueAdManager.zShowInterstitialInAdvance(this, MainActivity::class.java);
            }
        }
    }
    /* binding.splashStartBtn.setOnClickListener {
         if (!TrueZSPRepository.getIfAdmobAvailable(this@SplashScreenActivity)) {
             if (TrueConstants.isNetworkAvailable(this) && TrueConstants.isNetworkSpeedHigh()) {
                 binding.splashProgress.visibility = View.GONE
                 TrueAdManager.zShowInterstitialWithOutCallBacks(
                     this,
                     "ca-app-pub-3940256099942544/1033173712",
                     object : TrueAdCallBackInterface {
                         override fun onShowAdComplete() {
                             if (TrueZSPRepository.getIfAdAvailable(this@SplashScreenActivity)) {
                                 Toast.makeText(
                                     this@SplashScreenActivity,
                                     "Value Is 1 : " + TrueZSPRepository.getIfAdmobAvailable(this@SplashScreenActivity),
                                     Toast.LENGTH_SHORT
                                 ).show()
                                 startActivity(
                                     Intent(
                                         this@SplashScreenActivity,
                                         MainActivity::class.java
                                     )
                                 )
                             } else {
                                 Toast.makeText(
                                     this@SplashScreenActivity,
                                     "Value Is 2  : " + TrueZSPRepository.getIfAdmobAvailable(
                                         this@SplashScreenActivity
                                     ),
                                     Toast.LENGTH_SHORT
                                 ).show()
                                 startActivity(
                                     Intent(
                                         this@SplashScreenActivity,
                                         MainActivity::class.java
                                     )
                                 )
                             }
                         }
                     })
             } else {
                 startActivity(
                     Intent(
                         this@SplashScreenActivity,
                         MainActivity::class.java
                     )
                 )
             }
         } else {
             Toast.makeText(
                 this,
                 "Value Is: 3" + TrueZSPRepository.getIfAdmobAvailable(this),
                 Toast.LENGTH_SHORT
             ).show()
             if (TrueConstants.isNetworkAvailable(this) && TrueConstants.isNetworkSpeedHigh()) {
                 binding.splashProgress.visibility = View.GONE
                 TrueAdManager.zShowFBInterstitialWithOutCallBacks(
                     this,
                     "IMG_16_9_APP_INSTALL#418698876951921_418702163618259",
                     object : TrueAdCallBackInterface {
                         override fun onShowAdComplete() {
                             if (TrueZSPRepository.getIfFBAdAvailable(this@SplashScreenActivity)) {
                                 startActivity(
                                     Intent(
                                         this@SplashScreenActivity,
                                         MainActivity::class.java
                                     )
                                 )
                             } else {
                                 startActivity(
                                     Intent(
                                         this@SplashScreenActivity,
                                         MainActivity::class.java
                                     )
                                 )
                             }
                         }
                     })
             } else {
                 startActivity(
                     Intent(
                         this@SplashScreenActivity,
                         MainActivity::class.java
                     )
                 )
             }
         }
     }*/
}