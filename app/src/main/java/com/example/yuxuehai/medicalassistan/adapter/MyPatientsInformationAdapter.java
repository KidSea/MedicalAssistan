package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.xdandroid.simplerecyclerview.Adapter;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public abstract class MyPatientsInformationAdapter extends Adapter{

    public ArrayList<SampleBean> mSampleBeen;
    private Context mContext;

    public MyPatientsInformationAdapter(Context context){
        mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.patient_item_layout, parent, false);

        MyPatientsInformationAdapter.MyViewHolder mHolder = new MyViewHolder(view);
        return mHolder;

    }

    @Override
    protected void onViewHolderBind(RecyclerView.ViewHolder holder, int position, int viewType) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Patient patient = (Patient) mSampleBeen.get(myViewHolder.getLayoutPosition()).mBmobObject;
        Glide.with(mContext).load(patient.getPhoto().getFileUrl()).into(myViewHolder.mImageView);
        myViewHolder.mPatientname.setText(patient.getPatientName());
        myViewHolder.mGender.setText(patient.getGender());
        myViewHolder.mAge.setText(String.valueOf(patient.getAge()));
        myViewHolder.mPathology.setText(patient.getPathology());
        myViewHolder.mEntrytime.setText(patient.getEntryTime());
        myViewHolder.mRoomnum.setText(patient.getNum()+"床");

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

    public Patient getBean(int position){
        return (Patient) mSampleBeen.get(position).mBmobObject;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public TextView mRoomnum;
        public ImageView mImageView;
        public TextView mPatientname;
        public TextView mGender;
        public TextView mAge;
        public TextView mPathology;
        public TextView mEntrytime;


        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.im_icon);
            mPatientname = (TextView) itemView.findViewById(R.id.tv_patient_name);
            mGender = (TextView) itemView.findViewById(R.id.tv_patient_gender);
            mAge = (TextView) itemView.findViewById(R.id.tv_patient_age);
            mPathology = (TextView) itemView.findViewById(R.id.tv_patient_pathology);
            mEntrytime = (TextView) itemView.findViewById(R.id.tv_patient_entrytime);
            mRoomnum = (TextView) itemView.findViewById(R.id.tv_room_num);
        }
    }

}
