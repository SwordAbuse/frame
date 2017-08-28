package net.fengyun.utils;

import android.util.Log;

/**
 * log的工具类
 */

public class LogUtil {

    private static final String TAG = "LogUtil";

    private static  LogUtil logUtil;
    //是否显示log
    private static final boolean isShow = true;

    static {
        if (logUtil == null) {
            logUtil = new LogUtil();
        }
    }


    public final static LogUtil getInstance(){
        return logUtil;
    }
    private LogUtil() {

    }


    public void e(String error) {
        if (isShow)
            Log.e(TAG, error);
    }

    public void i(String info) {
        if (isShow)
            Log.i(TAG, info);
    }

    public void d(String debug) {
        if (isShow)
            Log.d(TAG, debug);
    }

}
