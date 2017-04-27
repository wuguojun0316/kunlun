package com.liangcang.kunlun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liangcang.kunlun.Bean.UserTel;
import com.liangcang.kunlun.R;

import java.util.List;

/**
 * Created by Wuguojun on 16/9/24.
 */
public class UserTelAdapter extends RecyclerView.Adapter
{
    private static final String TAG = UserTelAdapter.class.getSimpleName();
    private List<UserTel> list;
    private Context context;

    public interface OnRecyclerViewListener
    {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener)
    {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public UserTelAdapter(List<UserTel> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // 创建Item的view对象
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        TelHolder holder = new TelHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position)
    {
        TelHolder holder = (TelHolder) viewHolder;
        holder.position = position;
        holder.tvName.setText(list.get(position).getName());
        holder.tvTel.setText(list.get(position).getTel());

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class TelHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView tvName;
        public TextView tvTel;
        public int position;

        public TelHolder(View itemView)
        {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTel = (TextView) itemView.findViewById(R.id.tvTel);
            rootView = itemView.findViewById(R.id.llItem);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if(onRecyclerViewListener != null)
        {
            onRecyclerViewListener.onItemClick(position);
        }
        }

        @Override
        public boolean onLongClick(View v)
        {
            if(onRecyclerViewListener != null)
            {
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}