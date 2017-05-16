package com.liangcang.kunlun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 二维码扫描宿主Activity
 * Created by Wuguojun on 17/4/27.
 */

public class ZxingActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化组件
     */
    public void initViews()
    {

    }

    /**
     * 初始化数据
     *
     */

    public void initData()
    {

    }

<<<<<<< HEAD
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case 1:
                Log.d("onClick", "1");
                break;

            case 2:
                break;
        }
    }
=======
>>>>>>> 94c42f5435b2120f4a301831e757a04db93aa60a

}
