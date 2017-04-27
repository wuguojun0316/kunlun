package com.android.volley;

import org.apache.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

/**
 * 网络请求返回的响应头和数据
 * Created by Wuguojun on 16/8/13.
 */
public class NetworkResponse
{
    /**
     * HTTP请求返回状态码
     */
    public final int statusCode;

    /**
     * 返回的原始数据
     */
    public final byte[] data;

    /**
     * 响应头键值对
     */
    public final Map<String, String> headers;

    /**
     * 如果服务器返回状态码304，则为true
     */
    public final boolean notModified;

    public NetworkResponse(int statusCode, byte[] data, Map<String, String> headers, boolean notModified)
    {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
    }

    public NetworkResponse(byte[] data)
    {
        this(HttpStatus.SC_OK, data, Collections.<String, String>emptyMap(), false);
    }

    public NetworkResponse(byte[] data, Map<String, String> headers)
    {
        this(HttpStatus.SC_OK, data, headers, false);
    }

}
