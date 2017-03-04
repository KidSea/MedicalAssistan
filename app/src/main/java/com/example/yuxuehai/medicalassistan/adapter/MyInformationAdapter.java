package com.example.yuxuehai.medicalassistan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.xdandroid.simplerecyclerview.SingleViewTypeAdapter;
import com.xdandroid.simplerecyclerview.stickyheaders.StickyRecyclerHeadersAdapter;

import java.util.List;

/**
 * Created by yuxuehai on 17-2-20.
 */

public abstract class MyInformationAdapter extends SingleViewTypeAdapter<SampleBean> implements StickyRecyclerHeadersAdapter<MyInformationAdapter.MyHeaderHolder> {



    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(List<SampleBean> list, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rec_item_layout, parent, false);

        MyInformationAdapter.MyViewHolder mHolder = new MyViewHolder(view);
        return mHolder;
    }

    @Override
    protected void onViewHolderBind(List<SampleBean> list, RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        (myViewHolder).mImageView.setImageResource(R.drawable.ic_logo);
        SampleBean bean = list.get(holder.getLayoutPosition());
        if(bean.type == SampleBean.TYPE_PATIENT){
            Patient patient = (Patient) bean.mBmobObject;
            if (patient != null){
                (myViewHolder).mTextView.setText(patient.getWard().getRoomName()+patient.getNum()+patient.getDetail());
            }else {
                (myViewHolder).mTextView.setText("该病床没有病人");
            }

        }

    }


    @Override
    protected int getItemSpanSizeForGrid(int position, int viewType, int spanCount) {
        return 1;
    }

    @Override
    public long getHeaderId(int position) {
        return position / 6;
    }

    @Override
    public MyHeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rec_header_item_layout, parent, false);

        MyInformationAdapter.MyHeaderHolder mHolder = new MyHeaderHolder(view);

        return mHolder;
    }

    @Override
    public void onBindHeaderViewHolder(MyHeaderHolder holder, int position) {
        holder.mTextView.setText("病房：" + getHeaderId(holder.getLayoutPosition()) /* 当前 header 是哪一组的 header */ +
                ": Adapter Position " + String.valueOf(holder.getLayoutPosition() + 1) + " - " + String.valueOf(holder.getLayoutPosition() + 6));
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

    public static class MyHeaderHolder extends RecyclerView.ViewHolder{


        public TextView mTextView;

        public MyHeaderHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }
}
