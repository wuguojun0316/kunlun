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

    public void initData()
    {

    }

    public void initView()
    {

    }

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

}
