package com.liangcang.kunlun.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liangcang.kunlun.Bean.UserTel;
import com.liangcang.kunlun.R;
import com.liangcang.kunlun.adapter.UserTelAdapter;

import java.util.ArrayList;
import java.util.List;

public class FloatActivity extends AppCompatActivity
{
    private List<UserTel> list;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    private UserTelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MADAN");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        initView();

    }

    public void initData()
    {
        list = new ArrayList<UserTel>();
        for(int i = 0; i < 100; i++)
        {
            UserTel userTel = new UserTel();
            userTel.setName("wuguojun" + "-" + i);
            userTel.setTel("15070032620");
            list.add(userTel);
        }
        adapter = new UserTelAdapter(list, this);
    }

    public void initView()
    {
        initData();
//        recyclerView = (RecyclerView) findViewById(R.id.rvList);
//        actionButton = (FloatingActionButton) findViewById(R.id.fab);
//        actionButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }

}
