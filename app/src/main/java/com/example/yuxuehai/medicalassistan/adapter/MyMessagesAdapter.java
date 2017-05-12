package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.Notifications;
import com.xdandroid.simplerecyclerview.Adapter;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public abstract class MyMessagesAdapter extends Adapter{

    public ArrayList<Notifications> mList;
    private Context mContext;

    public MyMessagesAdapter(Context context){
        mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.mes_content_view, parent, false);

        MyMessagesAdapter.MyViewHolder mHolder = new MyViewHolder(view);
        return mHolder;

    }

    @Override
    protected void onViewHolderBind(RecyclerView.ViewHolder holder, int position, int viewType) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        Notifications notifications = mList.get(position);
        if(notifications.getCategory().equals("紧急")){
            myViewHolder.mImageView.setImageResource(R.drawable.ic_merg_mes);
            myViewHolder.mMode.setText(notifications.getCategory());
            myViewHolder.mContent.setText(notifications.getContent());
            myViewHolder.mTimeMode.setText(notifications.getTime().substring(0, 10));
            myViewHolder.mTime.setText(notifications.getTime().substring(11, 16));
        }else {
            myViewHolder.mImageView.setImageResource(R.drawable.ic_common_mes);
            myViewHolder.mMode.setText(notifications.getCategory());
            myViewHolder.mContent.setText(notifications.getContent());
            myViewHolder.mTimeMode.setText(notifications.getTime().substring(0, 10));
            myViewHolder.mTime.setText(notifications.getTime().substring(11, 16));
        }

    }


    @Override
    protected int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    protected int getItemSpanSizeForGrid(int position, int viewType, int spanCount) {
        return 1;
    }


    public void setList(ArrayList<Notifications> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mMode;
        TextView mContent;
        TextView mTimeMode;
        TextView mTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_icon);
            mMode = (TextView) itemView.findViewById(R.id.tv_mode);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
            mTimeMode = (TextView) itemView.findViewById(R.id.tv_time_mode);
            mTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

}
