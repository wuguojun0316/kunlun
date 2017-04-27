package com.android.volley;

/**
 * Created by Wuguojun on 16/8/13.
 * Volley超时重试策略抽象类
 */
public interface RetryPolicy
{
    /**
     * 返回请求超时时长
     * @return
     */
    public int getCurrentTimeout();

    /**
     * 返回请求超时后的重试次数
     */
    public int getCurrentRetryCount();

    /**
     * 重试
     */
    public void retry(VolleyError error) throws VolleyError;

}
