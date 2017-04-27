package com.liangcang.kunlun.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liangcang.kunlun.Bean.UserTel;
import com.liangcang.kunlun.R;
import com.liangcang.kunlun.adapter.UserTelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListActivityFragment extends Fragment implements UserTelAdapter.OnRecyclerViewListener
{
    private List<UserTel> list;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    private UserTelAdapter adapter;

    public ListActivityFragment()
    {

    }

    public void initData()
    {
        list = new ArrayList<UserTel>();
        for(int i = 0; i < 100; i++)
        {
            UserTel userTel = new UserTel();
            userTel.setName("Tomcat" + "-" + i);
            userTel.setTel("15070000000");
            list.add(userTel);
        }
        adapter = new UserTelAdapter(list, getActivity());
        adapter.setOnRecyclerViewListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initData();
        recyclerView = (RecyclerView) view.findViewById(R.id.rvList);
//        actionButton = (FloatingActionButton) view.findViewById(R.id.fab);
//        actionButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onItemClick(int position)
    {
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(int position)
    {
        Toast.makeText(getActivity(), String.valueOf(position) + "-L", Toast.LENGTH_SHORT).show();
        return true;
    }
}
