package com.android.volley;

/**
 * Created by Wuguojun on 16/8/13.
 */
@SuppressWarnings("serial")
public class VolleyError extends Exception
{
    public final NetworkResponse networkResponse;

    public VolleyError()
    {
        networkResponse = null;
    }

    public VolleyError(NetworkResponse response)
    {
        this.networkResponse = response;
    }

    public VolleyError(String exceptionMessage)
    {
        super(exceptionMessage);
        networkResponse = null;
    }

    public VolleyError(Throwable cause)
    {
        super(cause);
        networkResponse = null;
    }
}
