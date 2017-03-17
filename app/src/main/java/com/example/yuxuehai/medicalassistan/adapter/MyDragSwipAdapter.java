package com.example.yuxuehai.medicalassistan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.widget.PtrSwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by yuxuehai on 17-3-17.
 */

public class MyDragSwipAdapter extends SwipeMenuAdapter<MyDragSwipAdapter.MyViewHolder> {

    private List<Integer> mDatas;

    @Override
    protected View createContentView(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.content_view, parent, false);
        return contentView;
    }

    @Override
    protected LinearLayout createMenuView(ViewGroup parent, int viewType) {
        LinearLayout menuView = (LinearLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.menu_view, parent, false);
        return menuView;
    }

    @Override
    public MyViewHolder onCreateThisViewHolder(ViewGroup contentView, int viewType) {
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindThisViewHolder(MyViewHolder holder, int position) {
//        if (null != mDatas && position < mDatas.size()){
//            holder.mTextView.setText("content" + mDatas.get(position));
//        }
    }

    @Override
    public int getThisItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class MyViewHolder extends PtrSwipeMenuRecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }

    public void setDatas(List<Integer> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

}
