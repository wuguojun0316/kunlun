package com.android.volley;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Wuguojun on 17/2/6.
 */
public interface Cache
{
    public Entry get(String key);

    public void put(String key, Entry entry);

    public void initialize();

    public void invalidate(String key, boolean fullExpire);

    public void remove(String key);

    public void clear();

    /**
     * 缓存返回的实体的数据和元数据
     */
    public static class Entry
    {
        /**
         * 从缓存中返回的数据(body实体)
         */
        public byte[] data;

        /**
         * 验证缓存新鲜度的ETag
         */
        public String etag;

        /**
         * 从服务器返回的响应时间
         */
        public long serverDate;

        /**
         * 缓存的过期时间
         */
        public long ttl;

        /**
         * 缓存的新鲜时间
         */
        public long softTtl;

        /**
         * 从服务器返回的响应头字段,不可变且非空
         */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /**
         * 如果实体过期则为True
         */
        public boolean isExpired()
        {
            return this.ttl < System.currentTimeMillis();
        }

        /**
         * 如果需要刷新,则返回True
         */
        public boolean refreshNeeded()
        {
            return this.softTtl < System.currentTimeMillis();
        }


    }
}
