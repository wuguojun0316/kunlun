package com.liangcang.kunlun.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liangcang.kunlun.Bean.UserTel;
import com.liangcang.kunlun.R;
import com.liangcang.kunlun.adapter.DividerItemDecoration;
import com.liangcang.kunlun.adapter.UserTelAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements UserTelAdapter.OnRecyclerViewListener
{
    private List<UserTel> list;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    private UserTelAdapter adapter;
    private int index = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.rvList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, list));
        recyclerView.setAdapter(adapter);

    }

    public void initData()
    {
        list = new ArrayList<UserTel>();
        String temp = "1";
        for(int i = 0; i < 100; i++)
        {
            UserTel userTel = new UserTel();
            if(i % 4 == 0)
            {
                temp = i + "";
            }
            userTel.setTag(temp);
            userTel.setName("wuguojun" + "-" + i);
            userTel.setTel("15070032620");
            list.add(userTel);
        }
        adapter = new UserTelAdapter(list, this);
        adapter.setOnRecyclerViewListener(this);
    }


    @Override
    public void onItemClick(int position)
    {
        UserTel userTel = new UserTel();
        userTel.setName("Android" + "-" + index );
        userTel.setTel("15070032620");
        index++;
        list.add(2, userTel);
        adapter.notifyItemInserted(2);
        adapter.notifyItemRangeChanged(2, adapter.getItemCount());
    }

    @Override
    public boolean onItemLongClick(int position)
    {
        return false;
    }

}
