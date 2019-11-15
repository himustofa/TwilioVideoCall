package com.quickstart.twiliovideocall.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class Network {

    private static final String TAG = "Network";
    //https://gist.github.com/emil2k/5130324

    //===============================================| Check Internet Connection
    public static boolean haveNetwork(final Activity activity) {
        boolean haveWifi = false;
        boolean haveMobileData = false;

        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo[] infos = manager.getAllNetworkInfo();
            for (NetworkInfo info : infos) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    if (info.isConnected()) {
                        haveWifi = true;
                    }
                }
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    if (info.isConnected()) {
                        haveMobileData = true;
                    }
                }
            }
        }
        return haveWifi | haveMobileData;
    }

    //should check null because in airplane mode it will be null
    public static String getMobileDataLevel(Context context) {
        String speed = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ConnectivityManager mManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mManager != null) {
                NetworkCapabilities mNetwork = mManager.getNetworkCapabilities(mManager.getActiveNetwork());
                if (mNetwork != null) {
                    float d = mNetwork.getLinkDownstreamBandwidthKbps();
                    float u = mNetwork.getLinkUpstreamBandwidthKbps();
                    speed = "Down: " + d + " Kbps | Up: " + u + " Kbps ";
                }
            }
        }
        return speed;
    }

    public static int getWifiLevel(Activity activity) {
        int level = 0;
        if (haveNetwork(activity)) {
            WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                int linkSpeed = wifiManager.getConnectionInfo().getRssi();
                level = WifiManager.calculateSignalLevel(linkSpeed, 5);
            }
        }
        return level;
    }

    public static String getNetworkClass(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null || !info.isConnected())
                return null; // not connected
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
                return "WIFI";
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int networkType = info.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:     // api< 8: replace by 11
                    case TelephonyManager.NETWORK_TYPE_GSM:      // api<25: replace by 16
                        return "2G";
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:   // api< 9: replace by 12
                    case TelephonyManager.NETWORK_TYPE_EHRPD:    // api<11: replace by 14
                    case TelephonyManager.NETWORK_TYPE_HSPAP:    // api<13: replace by 15
                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA: // api<25: replace by 17
                        return "3G";
                    case TelephonyManager.NETWORK_TYPE_LTE:      // api<11: replace by 13
                    case TelephonyManager.NETWORK_TYPE_IWLAN:    // api<25: replace by 18
                    case 19: // LTE_CA
                        return "4G";
                    case TelephonyManager.NETWORK_TYPE_NR:       // api<29: replace by 20
                        return "5G";
                    default:
                        return "?";
                }
            }
        }
        return "?";
    }

    //===============================================| Check Location Connection
    public static boolean isEnabledLocation(final Activity activity) {
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            try {
                gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*if(!gpsEnabled && !networkEnabled) {
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(myIntent);
            }*/
        }
        return gpsEnabled && networkEnabled;
    }


}
