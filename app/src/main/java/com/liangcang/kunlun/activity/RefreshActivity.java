package com.liangcang.kunlun.activity;

import android.content.Context;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liangcang.kunlun.R;
import com.liangcang.kunlun.base.BaseActivity;
import com.liangcang.kunlun.widget.SwipeRefreshLayoutView;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends BaseActivity implements SwipeRefreshLayoutView.OnLoadListener
{
    private SwipeRefreshLayoutView mSwipeRefreshLayout;
    private ListView lvList;

    private List<String> mList;
    private StringAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayout()
    {
        return R.layout.activity_refresh;
    }

    @Override
    protected void initData()
    {
        super.initData();
    }

    @Override
    protected void initView()
    {
        super.initView();
        mSwipeRefreshLayout = (SwipeRefreshLayoutView) findViewById(R.id.srLayout);
        lvList = (ListView) findViewById(R.id.lvList);
        mList = new ArrayList<String>();
        // 初始化SwipeRefreshLayout
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getColor(android.R.color.white));
        mSwipeRefreshLayout.setColorSchemeColors(getColor(R.color.colorAccent), getColor(R.color.colorPrimary), getColor(R.color.light_gray));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mList.clear();
                        for(int i = 0; i < 50; i++)
                        {
                            mList.add("大雪满弓刀");
                            mAdapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }


        });
        mSwipeRefreshLayout.setOnLoadListener(this);
        for (int i = 0; i < 20; i++)
        {
            mList.add("青山有幸埋忠骨");
        }
        mAdapter = new StringAdapter(this);
        lvList.setAdapter(mAdapter);
    }

    @Override
    protected String initTitle()
    {
        return "刷新加载";
    }

    private class StringAdapter<String> extends BaseAdapter
    {
        private LayoutInflater mInflater;

        public StringAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return mList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder viewHolder = null;
            if(convertView == null)
            {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_recycler, null);
                viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
                viewHolder.tel = (TextView) convertView.findViewById(R.id.tvTel);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(mList.get(position));
            viewHolder.tel.setText("15070032620");
            return convertView;
        }

        class ViewHolder
        {
            TextView name;
            TextView tel;
        }

    }

    @Override
    public void onLoad()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
               for(int i = 0; i < 20; i++)
               {
                   mList.add("android");
                   mAdapter.notifyDataSetChanged();
               }
               mSwipeRefreshLayout.setLoading(false);
            }
        }, 1000);
    }
}
