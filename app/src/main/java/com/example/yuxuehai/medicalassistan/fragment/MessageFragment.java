package com.example.yuxuehai.medicalassistan.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.ViewPageFragmentAdapter;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class MessageFragment extends BaseViewPagerframent {





    @Override
    protected void addPagetoAdapter(ViewPageFragmentAdapter fragmentAdapter) {
        String[] titles = UIUtils.getStringArray(R.array.mes_viewpage_arrays);

        // 紧急消息
        fragmentAdapter.addTab(titles[0], Constants.ENGENCY_MES, DefaultFragment.class,
                getBundle(1));
        // 普通消息
        fragmentAdapter.addTab(titles[1], Constants.NORMAL_MES, DefaultFragment.class,
                getBundle(2));

    }


    @Override
    protected void setScreenPageLimit(ViewPager mViewPager) {
        // TODO Auto-generated method stub
        mViewPager.setOffscreenPageLimit(3);
    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        //bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        bundle.putString("key", "我是综合里的: " + newType);
        return bundle;
    }

    /**
     * 基类会根据不同的catalog展示相应的数据
     *
     * @param catalog
     *            要显示的数据类别
     * @return
     */
    private Bundle getBundle(String catalog) {
        Bundle bundle = new Bundle();
        // bundle.putString(BlogFragment.BUNDLE_BLOG_TYPE, catalog);
        bundle.putString("key", "我是综合里的: " + catalog);
        return bundle;
    }

}
