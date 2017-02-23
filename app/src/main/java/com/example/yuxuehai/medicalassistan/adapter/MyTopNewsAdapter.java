package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class MyTopNewsAdapter extends BaseRecyclerAdapter<String,MyTopNewsAdapter.MyViewHolder> {


    private Context mContext;

    public MyTopNewsAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        addDatas(data);
    }

    @Override
    public MyViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item_layout, parent, false);

        MyViewHolder mHolder = new MyViewHolder(view);



        return mHolder;
    }

    @Override
    public void onBind(MyViewHolder viewHolder, int RealPosition, String data) {
        (viewHolder).mImageView.setImageResource(R.drawable.ic_logo);
        (viewHolder).mTextView.setText(data);
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_res);
            mImageView = (ImageView) itemView.findViewById(R.id.im_icon);

        }
    }


}
