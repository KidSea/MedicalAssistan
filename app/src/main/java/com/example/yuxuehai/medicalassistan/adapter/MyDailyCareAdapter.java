package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class MyDailyCareAdapter extends RecyclerView.Adapter<MyDailyCareAdapter.MyViewHolder> {


    private ArrayList<String> data;
    private Context mContext;

    public MyDailyCareAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.data = data;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.rec_item_layout, null);

        MyViewHolder mHolder = new MyViewHolder(view);

        mHolder.mTextView = (TextView) view.findViewById(R.id.tv_res);
        mHolder.mImageView = (ImageView) view.findViewById(R.id.im_icon);


        return mHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mImageView.setImageResource(R.drawable.ic_launcher);
        holder.mTextView.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}
