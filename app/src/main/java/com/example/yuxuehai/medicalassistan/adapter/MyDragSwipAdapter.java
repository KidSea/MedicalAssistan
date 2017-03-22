package com.example.yuxuehai.medicalassistan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.widget.PtrSwipeMenuRecyclerView;

import java.util.Calendar;

import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by yuxuehai on 17-3-17.
 */

public class MyDragSwipAdapter extends SwipeMenuAdapter<Event,MyDragSwipAdapter.MyViewHolder> {


    private final Calendar mCalendar;

    public MyDragSwipAdapter(){
        mCalendar = Calendar.getInstance();
    }


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
        if (null != mDatas && position < mDatas.size()){

            Event event = mDatas.get(position);
            holder.mEvent.setText(event.getName());
            holder.mObject.setText(event.getObjectname());
            holder.mLocation.setText(event.getLocation());
            BmobDate bmobDate = event.getDate();
            holder.mTime.setText(bmobDate.getDate().substring(10,16));
            long timeStamp = BmobDate.getTimeStamp(bmobDate.getDate());
            mCalendar.setTimeInMillis(timeStamp);
            int apm = mCalendar.get(Calendar.AM_PM);
            if (apm == 0){
                holder.mTimeMode.setText("上午");
            }else {
                holder.mTimeMode.setText("下午");
            }

        }

    }

    @Override
    public int getThisItemCount() {
        return getDatas() == null ? 0 : getDatas().size();
    }

    public class MyViewHolder extends PtrSwipeMenuRecyclerView.ViewHolder {

        public TextView mEvent;
        public TextView mObject;
        public TextView mLocation;
        public TextView mTimeMode;
        public TextView mTime;


        public MyViewHolder(View itemView) {
            super(itemView);
            mEvent = (TextView) itemView.findViewById(R.id.tv_event);
            mObject = (TextView) itemView.findViewById(R.id.tv_object);
            mLocation = (TextView) itemView.findViewById(R.id.tv_location);
            mTimeMode = (TextView) itemView.findViewById(R.id.tv_time_mode);
            mTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }


}
