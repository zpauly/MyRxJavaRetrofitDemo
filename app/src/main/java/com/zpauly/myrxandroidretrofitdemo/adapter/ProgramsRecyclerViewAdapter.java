package com.zpauly.myrxandroidretrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zpauly.myrxandroidretrofitdemo.R;
import com.zpauly.myrxandroidretrofitdemo.entity.Program;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-5-6.
 */
public class ProgramsRecyclerViewAdapter extends RecyclerView.Adapter<ProgramsRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;

    private List<Program.ResultBean> mData = new ArrayList<>();

    public ProgramsRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public ProgramsRecyclerViewAdapter(Context context, List<Program.ResultBean> data) {
        mContext = context;
        mData.addAll(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview_item_program, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mCName.setText(mData.get(position).getCName());
        holder.mPName.setText(mData.get(position).getPName());
        holder.mTime.setText(mData.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mData.get(position).getPUrl()));
                mContext.startActivity(intent);
            }
        });
    }

    public void addData(Program.ResultBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void addAllData(List<Program.ResultBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mCName;

        TextView mPName;

        TextView mTime;

        public MyViewHolder(View itemView) {
            super(itemView);

            mCName = (TextView) itemView.findViewById(R.id.cname);
            mPName = (TextView) itemView.findViewById(R.id.pname);
            mTime = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
