package com.quickstart.twiliovideocall.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.quickstart.twiliovideocall.R;

public class MyNetworkReceiver extends BroadcastReceiver {

    /*
    Help Link:
    ===========
    https://stackoverflow.com/questions/12570621/efficient-approach-to-continuously-check-whether-internet-connection-is-availabl
    https://www.youtube.com/watch?v=C9Ai1xrthKo
    */

    private static final String TAG = "MyNetworkReceiver";
    private Context context;
    private Snackbar snackbar;

    public MyNetworkReceiver(Context context) {
        this.context = context;
        this.snackbar = Snackbar.make(((Activity)context).findViewById(android.R.id.content), context.getString(R.string.network_unavailable), Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if(isConnected) {
                if (snackbar != null) {
                    snackbar.dismiss();
                }
                Log.d(TAG, "Internet connection is connected");
            } else {
                if (snackbar != null) {
                    snackbar.show();
                }
                Log.d(TAG, "Internet connection is not connected");
            }

            String data = getMobileDataLevel(context, cm);
            if (data != null) {
                Toast.makeText(context, ""+data, Toast.LENGTH_LONG).show();
            }
        }
    }

    //should check null because in airplane mode it will be null
    public String getMobileDataLevel(Context context, ConnectivityManager connectivityManager) {
        String speed = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NetworkCapabilities mNetwork = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            //int downSpeed = nc.getLinkDownstreamBandwidthKbps();
            //int upSpeed = nc.getLinkUpstreamBandwidthKbps();
            if (mNetwork != null) {
                speed = "Downstream: " + mNetwork.getLinkDownstreamBandwidthKbps()  + " Kbps | Upstream: " + mNetwork.getLinkUpstreamBandwidthKbps() + " Kbps";
                Log.d(TAG, speed);
            }
        }
        return speed;
    }

    public int getWifiLevel(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int linkSpeed = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(linkSpeed, 5);
        return level;
    }

}
