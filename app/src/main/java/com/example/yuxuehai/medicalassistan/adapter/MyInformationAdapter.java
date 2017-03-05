package com.example.yuxuehai.medicalassistan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.xdandroid.simplerecyclerview.Adapter;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public abstract class MyInformationAdapter extends Adapter{

    public ArrayList<SampleBean> mSampleBeen;

    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.ward_item_layout, parent, false);

        MyInformationAdapter.MyViewHolder mHolder = new MyViewHolder(view);
        return mHolder;

    }

    @Override
    protected void onViewHolderBind(RecyclerView.ViewHolder holder, int position, int viewType) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        (myViewHolder).mImageView.setImageResource(R.drawable.ic_logo);
        Ward ward = (Ward) mSampleBeen.get(position).mBmobObject;
        (myViewHolder).mTextView.setText(ward.getRoomName()+ward.getLocation());
    }

    @Override
    protected int getViewType(int position) {
        return mSampleBeen.get(position).type;
    }

    @Override
    protected int getCount() {
        return mSampleBeen != null ? mSampleBeen.size() : 0;
    }

    @Override
    protected int getItemSpanSizeForGrid(int position, int viewType, int spanCount) {
        return 1;
    }


    public void setList(ArrayList<SampleBean> mList) {
        mSampleBeen = mList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public TextView mTextView;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_res);
            mImageView = (ImageView) itemView.findViewById(R.id.im_icon);
        }
    }

}
