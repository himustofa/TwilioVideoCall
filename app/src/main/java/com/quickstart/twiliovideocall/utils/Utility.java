package com.quickstart.twiliovideocall.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Utility {

    //===============================================| ProgressDialog
    public static ProgressDialog showProgressDialog(Context mActivity, final String message, boolean isCancelable) {
        ProgressDialog mProgress = new ProgressDialog(mActivity);
        mProgress.show();
        mProgress.setCancelable(isCancelable); //setCancelable(false); = invisible clicking the outside
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.setMessage(message);
        return mProgress;
    }

    public static void dismissProgressDialog(ProgressDialog mProgress) {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

}
