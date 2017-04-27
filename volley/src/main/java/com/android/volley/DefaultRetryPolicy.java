/**
 * org.apache.http.conn.ConnectTimeoutException
 * 当链接HTTP服务或者从HttpConnectionManager中得到一个可用的连接超时时，抛出该异常；
 * java.net.SocketTimeoutException
 * 当数据传输未在规定时间完成，则抛出该异常；
 * DefaultRetryPolicy中的默认最大mMaxNumRetries为1，mCurrentTimeoutMs为2500毫秒；
 */

package com.android.volley;

/**
 * Volley默认的请求超时重试策略
 * Created by Wuguojun on 16/8/13.
 */
public class DefaultRetryPolicy implements RetryPolicy
{
    /**
     * 当前请求超时时长
     */
    private int mCurrentTimeoutMs;

    /**
     * 当前请求重试次数
     */
    private int mCurrentRetryCount;

    /**
     * 最大重试数
     */
    private final int mMaxNumRetries;

    /**
     * 乘数因子，每次重试请求时，超时时长都要乘以该因子
     */
    private final float mBackofMultiplier;

    /**
     * 默认的数据传输超时时长
     */
    private static final int DEFAULT_TIMEOUT_MS = 2500;

    /**
     * 默认的最大的重试数
     */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /**
     * 默认的乘数因子
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    public DefaultRetryPolicy()
    {
        this(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
    }

    public DefaultRetryPolicy(int initialTimeoutMs, int maxNumRtries, float backoffMultiplier)
    {
        mCurrentTimeoutMs = initialTimeoutMs;
        mMaxNumRetries = maxNumRtries;
        mBackofMultiplier = backoffMultiplier;
    }

    @Override
    public int getCurrentTimeout()
    {
        return mCurrentTimeoutMs;
    }

    @Override
    public int getCurrentRetryCount()
    {
        return mCurrentRetryCount;
    }

    @Override
    public void retry(VolleyError error) throws VolleyError
    {
        mCurrentRetryCount++;        // 当前重试次数
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackofMultiplier);     // 当前超时时长
        if(!hasAttemptRemaining())
        {
            throw error;
        }
    }

    protected boolean hasAttemptRemaining()
    {
        return mCurrentRetryCount <= mMaxNumRetries;
    }
}
