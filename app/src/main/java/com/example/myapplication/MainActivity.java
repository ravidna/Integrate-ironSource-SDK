package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;


public class MainActivity extends AppCompatActivity {

    private final String PLACEMENT_NAME = "DefaultInterstitial";
    private final String APP_KEY = "b797a90d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Validate the integration.
        IntegrationHelper.validateIntegration(this);
        //Network Status
        IronSource.shouldTrackNetworkState(this, true);
    }
    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    /** Called when the user taps the Initialize button */
    public void sendInitialize(View view) {
        IronSource.init(this, APP_KEY);
        Log.d("TAG", "first");
        IronSource.setInterstitialListener(new InterstitialListener() {

            /**
             Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
                Log.d("TAG", "onInterstitialAdReady");
            }

            // called when the interstitial has failed to load
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                Log.d("TAG", "onInterstitialAdLoadFailed");
            }

            /**
             Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
                Log.d("TAG", "onInterstitialAdOpened");
            }

            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
                Log.d("TAG", "onInterstitialAdClosed");
            }

            /*
             * Invoked when the ad was opened and shown successfully.
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
                Log.d("TAG", "onInterstitialAdShowSucceeded");
            }

            /**
             * Invoked when Interstitial ad failed to show.
             // @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
                Log.d("TAG", "onInterstitialAdShowFailed");
            }

            /*
             * Invoked when the end user clicked on the interstitial ad.
             */
            @Override
            public void onInterstitialAdClicked() {
                Log.d("TAG", "AdClicked");
            }
        });
        Log.d("TAG", "second");
    }

    /** Called when the user taps the load button */
    public void sendLoad(View view) {
        IronSource.loadInterstitial();
    }

    /** Called when the user taps the show button */
    public void sendShow(View view) {
        // check if interstitial is available
        if (IronSource.isInterstitialReady()) {
            //show the interstitial
            IronSource.showInterstitial(PLACEMENT_NAME);
        }
        else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
    }


}
