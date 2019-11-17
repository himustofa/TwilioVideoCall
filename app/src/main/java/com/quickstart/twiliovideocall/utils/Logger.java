package com.quickstart.twiliovideocall.utils;

public class Logger {

    private static Logger mInstance;

    public static Logger getInstance() {
        if (mInstance == null) {
            mInstance = new Logger();
        }
        return mInstance;
    }

    public void Log(String msg) {
        System.out.println("Twilio Video App: " + msg);
    }
}
