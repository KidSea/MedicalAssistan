package com.example.yuxuehai.medicalassistan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class MyGrideViewAdapter extends BaseAdapter {

    ArrayList<ItemData> mItemDatas;
    private final LayoutInflater mInflater;

    public MyGrideViewAdapter(ArrayList<ItemData> itemDatas, Context context){
        this.mItemDatas = itemDatas;
        this.mInflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItemDatas.size();
    }

    @Override
    public ItemData getItem(int position) {
        return mItemDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_my_gridview,null);
            holder.mIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.mTitle = (TextView) convertView.findViewById(R.id.tv_icon_name);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mItemDatas.get(position).getTitles());
        holder.mIcon.setImageResource(mItemDatas.get(position).getImageId());

        return convertView;
    }

    static class ViewHolder{
        private TextView mTitle;
        private ImageView mIcon;
    }
}
