package com.android.volley;

/**
 * Created by Wuguojun on 17/2/4.
 */

public interface Network
{
    public NetworkResponse performRequest(Request<?> request) throws VolleyError;
}
