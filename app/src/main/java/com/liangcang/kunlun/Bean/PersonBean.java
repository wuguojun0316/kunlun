package com.liangcang.kunlun.Bean;

import java.util.List;

/**
 * Created by Wuguojun on 17/2/10.
 */
public class PersonBean
{

    /**
     * ret : 1
     * msg :
     * data : [{"tid":"b4221c54-8437-4763-8c8a-348260c8d324","pdt_name":"消防标识"},{"tid":"e3bd909c-d8b6-401b-979c-5e72bc75790a","pdt_name":"消防漫画"},{"tid":"15ca0f97-930e-4938-9079-30bdae0de89f","pdt_name":"消防标语"}]
     */

    private String ret;
    private String msg;
    private List<DataBean> data;

    public String getRet()
    {
        return ret;
    }

    public void setRet(String ret)
    {
        this.ret = ret;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * tid : b4221c54-8437-4763-8c8a-348260c8d324
         * pdt_name : 消防标识
         */

        private String tid;
        private String pdt_name;

        public String getTid()
        {
            return tid;
        }

        public void setTid(String tid)
        {
            this.tid = tid;
        }

        public String getPdt_name()
        {
            return pdt_name;
        }

        public void setPdt_name(String pdt_name)
        {
            this.pdt_name = pdt_name;
        }
    }
}
