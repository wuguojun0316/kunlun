package com.liangcang.kunlun.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.liangcang.kunlun.R;

import java.util.logging.Logger;

/**
 * Created by Wuguojun on 17/4/12.
 */
public class SwipeRefreshLayoutView extends SwipeRefreshLayout
{
    private ListView mListView;
    private View mFooterView;
    private int mScaleTouchSlop;

    private float mDownY, mUpY;      // 触摸事件的Y轴坐标值

    private boolean isLoading;       // 正在加载状态

    private OnLoadListener mOnLoadListener;

    public SwipeRefreshLayoutView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // 加载更多提示item
        mFooterView = View.inflate(context, R.layout.common_footer, null);
        // 控件移动的最小距离，手指移动的距离大于这个值才能拖动控件
        mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        // 获取ListView，设置ListView的布局位置
        if(mListView == null)
        {
            // 判断有多少个子View
            if(getChildCount() > 0)
            {
                // 判断ListView是否是ListView
                if(getChildAt(0) instanceof ListView)
                {
                    // 创建ListView对象
                    mListView = (ListView) getChildAt(0);
                    // 设置ListView的滑动监听
                    setListViewOnSroll();
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        switch(ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时的点坐标
                mDownY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动过程中判断是否触发上拉加载更多
//                if(canLoadMore())
//                {
//                    loadData("SwipeRefreshlayout");
//                }
                break;

            case MotionEvent.ACTION_UP:
                // 手指松开是的点坐标
                mUpY = ev.getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是否满足上拉加载更多条件
     *
     * @return
     */
    private boolean canLoadMore()
    {
        // 第一，判断是否是上拉动作
        boolean condition1 = (mDownY - mUpY) >= mScaleTouchSlop;
        //Log.d("上拉", condition1 + "");

        // 第二, 判断当前页面可见的最后一个item是否是最后一个item
        boolean condition2 = false;
        if(mListView != null && mListView.getAdapter() != null)
        {
            condition2 = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        // Log.d("最后条目", "" + condition2);
        // 第三，判断是否是加载状态
        boolean condition3 = !isLoading;
        //Log.d("加载状态", condition3 + "");
        return condition1 && condition2 && condition3;
    }

    /**
     * 处理加载数据的逻辑
     */
    private void loadData(String flag)
    {
        if(mOnLoadListener != null)
        {
            // 设置加载状态，显示加载item
            setLoading(true);
            mOnLoadListener.onLoad();
            Log.d("flag", flag);
        }
    }

    /**
     * 设置加载状态，是否加载根据传入的boolean值进行判断
     * @param loading
     */
    public void setLoading(boolean loading)
    {
        // 修改当前的状态
        isLoading = loading;
        if(isLoading)
        {
            // 显示加载item
            mListView.addFooterView(mFooterView);
        }
        else
        {
            // 隐藏加载item
            mListView.removeFooterView(mFooterView);
            // 重置滑动的坐标
            mDownY = 0;
            mUpY = 0;
        }
    }

    /**
     * 设置ListView的滑动监听
     */
    private void setListViewOnSroll()
    {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
                // 移动过程中判断是否下拉加载更多
                if(canLoadMore())
                {
                    loadData("ListView");
                }
            }
        });
    }

    /**
     * 上拉加载的接口回调
     */
    public interface OnLoadListener
    {
        void onLoad();
    }

    public void setOnLoadListener(OnLoadListener listener)
    {
        this.mOnLoadListener = listener;
    }
}
