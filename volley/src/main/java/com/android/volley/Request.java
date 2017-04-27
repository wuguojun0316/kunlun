package com.android.volley;

/**
 * 网络请求的抽象基类
 * Created by Wuguojun on 17/2/4.
 */
public abstract class Request<T> implements Comparable<Request<T>>
{
    /**
     * POST和PUT请求参数的默认编码
     */
    private static final String DEFAULT_PARAMS_ENCONDING = "UTF-8";

    /**
     * 基类所支持的请求方式
     */
    public interface Method
    {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
    }




    @Override
    public int compareTo(Request<T> other)
    {
        return 0;
    }
}
