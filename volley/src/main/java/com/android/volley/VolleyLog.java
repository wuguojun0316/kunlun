package com.android.volley;

import android.util.Log;

/**
 * Created by Wuguojun on 16/8/13.
 */
public class VolleyLog
{
    public static String TAG = "Volley";

    public static boolean DEBUG = Log.isLoggable(TAG, Log.VERBOSE);

    public static void setTag(String tag)
    {
        //
    }

    public static void v(String format, Object... args)
    {
        if(DEBUG)
        {
            Log.v(TAG, "");
        }
    }

}
