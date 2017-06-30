package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class MyRemindTimeAdapter extends BaseRecyclerAdapter<String,
        MyRemindTimeAdapter.MyViewHolder> {


    private Context mContext;
    private int position;

    public MyRemindTimeAdapter(Context context, ArrayList<String> data, int selectedPosition) {
        this.mContext = context;
        position = selectedPosition;
        addDatas(data);
    }

    @Override
    public MyViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_remind_time, parent, false);

        MyViewHolder mHolder = new MyViewHolder(view);

        return mHolder;
    }

    @Override
    public void onBind(MyViewHolder viewHolder, int RealPosition, String data) {

        (viewHolder).mTextView.setText(data);
        viewHolder.mRadioButton.setChecked(RealPosition == position);
    }





    public static class MyViewHolder extends BaseRecyclerAdapter.Holder {


        public TextView mTextView;
        public RadioButton mRadioButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_res);
            mRadioButton  = (RadioButton) itemView.findViewById(R.id.rb_time_checked);
        }
    }



}
