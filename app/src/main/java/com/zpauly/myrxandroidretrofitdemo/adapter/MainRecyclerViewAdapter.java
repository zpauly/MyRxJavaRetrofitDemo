package com.zpauly.myrxandroidretrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zpauly.myrxandroidretrofitdemo.R;
import com.zpauly.myrxandroidretrofitdemo.activity.ProgramsActivity;
import com.zpauly.myrxandroidretrofitdemo.entity.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-5-6.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;

    private List<Channel.ResultBean> mData = new ArrayList<>();

    public MainRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public MainRecyclerViewAdapter(Context context, List<Channel.ResultBean> data) {
        mContext = context;
        mData.addAll(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview_item_main, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mData.get(position).getChannelName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProgramsActivity.class);
                intent.putExtra("CODE", mData.get(position).getRel());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(Channel.ResultBean data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void addAllData(List<Channel.ResultBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
