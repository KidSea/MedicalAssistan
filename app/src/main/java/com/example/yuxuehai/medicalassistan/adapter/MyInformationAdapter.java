package com.example.yuxuehai.medicalassistan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.xdandroid.simplerecyclerview.SingleViewTypeAdapter;
import com.xdandroid.simplerecyclerview.stickyheaders.StickyRecyclerHeadersAdapter;

import java.util.List;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class MyInformationAdapter extends SingleViewTypeAdapter<String> implements StickyRecyclerHeadersAdapter<MyInformationAdapter.MyViewHolder> {



    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(List<String> list, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rec_item_layout, parent, false);

        MyInformationAdapter.MyViewHolder mHolder = new MyViewHolder(view);




        return mHolder;
    }

    @Override
    protected void onViewHolderBind(List<String> list, RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        (myViewHolder).mImageView.setImageResource(R.drawable.ic_logo);
        (myViewHolder).mTextView.setText(list.get(position));
    }

    @Override
    protected void onLoadMore(Void please_make_your_adapter_class_as_abstract_class) {

    }

    @Override
    protected boolean hasMoreElements(Void let_activity_or_fragment_implement_these_methods) {
        return false;
    }

    @Override
    protected int getItemSpanSizeForGrid(int position, int viewType, int spanCount) {
        return 0;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public MyViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(MyViewHolder holder, int position) {
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
