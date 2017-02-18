package com.example.yuxuehai.medicalassistan.ui;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.GuidePagerAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.fragment.NavigationDrawerFragment;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

import java.util.ArrayList;

import static com.example.yuxuehai.medicalassistan.R.id.iv_find;
import static com.example.yuxuehai.medicalassistan.R.id.iv_find0;
import static com.example.yuxuehai.medicalassistan.R.id.iv_home;
import static com.example.yuxuehai.medicalassistan.R.id.iv_home0;
import static com.example.yuxuehai.medicalassistan.R.id.iv_me;
import static com.example.yuxuehai.medicalassistan.R.id.iv_me0;
import static com.example.yuxuehai.medicalassistan.R.id.tv_find;
import static com.example.yuxuehai.medicalassistan.R.id.tv_find0;
import static com.example.yuxuehai.medicalassistan.R.id.tv_home;
import static com.example.yuxuehai.medicalassistan.R.id.tv_home0;
import static com.example.yuxuehai.medicalassistan.R.id.tv_me;
import static com.example.yuxuehai.medicalassistan.R.id.tv_me0;

/**
 * Created by yuxuehai on 2017/2/12.
 * 主界面
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,View.OnClickListener{

    private CharSequence mTitle;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private LinearLayout mHome_layout;
    private LinearLayout mMessage_layout;
    private LinearLayout mExplore_layout;
    private LinearLayout mMine_layout;
    private ViewPager mCotentPager;
    private ImageView mIv_home;
    private ImageView mIv_home_selected;
    private ImageView mIv_message;
    private ImageView mIv_message_selected;
    private ImageView mIv_explore;
    private ImageView mIv_explore_selected;
    private ImageView mIv_mine;
    private ImageView mIv_mine_selected;
    private ImageView mAddView;
    private TextView mTv_home;
    private TextView mTv_home_selected;
    private TextView mTv_message;
    private TextView mTv_explore;
    private TextView mTv_message_selected;
    private TextView mTv_explore_selected;
    private TextView mTv_mine;
    private TextView mTv_mine_selected;

    private ArrayList<View> mViews;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_mytb);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mHome_layout = (LinearLayout) findViewById(R.id.bottom_home);
        mMessage_layout = (LinearLayout) findViewById(R.id.bottom_mess);
        mExplore_layout = (LinearLayout) findViewById(R.id.bottom_explore);
        mMine_layout = (LinearLayout) findViewById(R.id.bottom_mime);

        mCotentPager = (ViewPager) findViewById(R.id.vp_content);
        mAddView = (ImageView) findViewById(R.id.iv_add);

        mIv_home = (ImageView) findViewById(iv_home0);
        mIv_home_selected = (ImageView) findViewById(iv_home);
        mIv_message = (ImageView) findViewById(R.id.iv_mess0);
        mIv_message_selected = (ImageView) findViewById(R.id.iv_mess);
        mIv_explore = (ImageView) findViewById(iv_find0);
        mIv_explore_selected = (ImageView) findViewById(iv_find);
        mIv_mine = (ImageView) findViewById(iv_me0);
        mIv_mine_selected = (ImageView) findViewById(iv_me);

        mTv_home = (TextView) findViewById(tv_home0);
        mTv_home_selected = (TextView) findViewById(tv_home);
        mTv_message = (TextView) findViewById(R.id.tv_mess0);
        mTv_message_selected = (TextView) findViewById(R.id.tv_mess);
        mTv_explore = (TextView) findViewById(tv_find0);
        mTv_explore_selected = (TextView) findViewById(tv_find);
        mTv_mine = (TextView) findViewById(tv_me0);
        mTv_mine_selected = (TextView) findViewById(tv_me);

        mHome_layout.setOnClickListener(this);
        mMessage_layout.setOnClickListener(this);
        mExplore_layout.setOnClickListener(this);
        mMine_layout.setOnClickListener(this);
        mAddView.setOnClickListener(this);

        initAlpha();
        changeAlpha(0);
    }

    @Override
    protected void initData() {

        mViews = new ArrayList<>();

        LayoutInflater inflater = LayoutInflater.from(this);
        mViews.add(inflater.inflate(R.layout.home, null));
        mViews.add(inflater.inflate(R.layout.home1, null));
        mViews.add(inflater.inflate(R.layout.home2, null));
        mViews.add(inflater.inflate(R.layout.home3, null));

        mCotentPager.setAdapter(new GuidePagerAdapter(mViews));
        mCotentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeAlpha(position, positionOffsetPixels, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                initAlpha();
                changeAlpha(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //set up the drawer
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mToolbar);

        mTitle = getTitle();
    }

    protected void initAlpha() {
        mIv_home_selected.setAlpha(1.0f);
        mIv_home.setAlpha(0.0f);
        mIv_message_selected.setAlpha(1.0f);
        mIv_message.setAlpha(0.0f);
        mIv_explore_selected.setAlpha(1.0f);
        mIv_explore.setAlpha(0.0f);
        mIv_mine_selected.setAlpha(1.0f);
        mIv_mine.setAlpha(0.0f);

        mTv_home_selected.setAlpha(1.0f);
        mTv_home.setAlpha(0.0f);
        mTv_message_selected.setAlpha(1.0f);
        mTv_message.setAlpha(0.0f);
        mTv_explore_selected.setAlpha(1.0f);
        mTv_explore.setAlpha(0.0f);
        mTv_mine_selected.setAlpha(1.0f);
        mTv_mine.setAlpha(0.0f);
    }

    private void changeAlpha(int i) {
        if (i == 0) {
            if (mIv_home_selected.getAlpha() == 0) {
                mIv_home_selected.setAlpha(1.0f);
                mIv_home.setAlpha(0.0f);
                mTv_home_selected.setAlpha(1.0f);
                mTv_home.setAlpha(0.0f);
            } else if (mIv_home_selected.getAlpha() == 1) {
                mIv_home_selected.setAlpha(0.0f);
                mIv_home.setAlpha(1.0f);
                mTv_home_selected.setAlpha(0.0f);
                mTv_home.setAlpha(1.0f);
            }
        } else if (i == 1) {
            if (mIv_message_selected.getAlpha() == 0) {
                mIv_message_selected.setAlpha(1.0f);
                mIv_message.setAlpha(0.0f);
                mTv_message_selected.setAlpha(1.0f);
                mTv_message.setAlpha(0.0f);
            } else if (mIv_home_selected.getAlpha() == 1) {
                mIv_message_selected.setAlpha(0.0f);
                mIv_message.setAlpha(1.0f);
                mTv_message_selected.setAlpha(0.0f);
                mTv_message.setAlpha(1.0f);
            }
        } else if (i == 2) {
            if (mIv_explore_selected.getAlpha() == 0) {
                mIv_explore_selected.setAlpha(1.0f);
                mIv_explore.setAlpha(0.0f);
                mTv_explore_selected.setAlpha(1.0f);
                mTv_explore.setAlpha(0.0f);
            } else if (mIv_home_selected.getAlpha() == 1) {
                mIv_explore_selected.setAlpha(0.0f);
                mIv_explore.setAlpha(1.0f);
                mTv_explore_selected.setAlpha(0.0f);
                mTv_explore.setAlpha(1.0f);
            }
        } else if (i == 3) {
            if (mIv_home_selected.getAlpha() == 0) {
                mIv_mine_selected.setAlpha(1.0f);
                mIv_mine.setAlpha(0.0f);
                mTv_mine_selected.setAlpha(1.0f);
                mTv_mine.setAlpha(0.0f);
            } else if (mIv_home_selected.getAlpha() == 1) {
                mIv_mine_selected.setAlpha(0.0f);
                mIv_mine.setAlpha(1.0f);
                mTv_mine_selected.setAlpha(0.0f);
                mTv_mine.setAlpha(1.0f);
            }
        }
    }

    private void changeAlpha(int currPosition, int deltaPx, float progress) {

        int nextPosition;

        if (deltaPx > 0) {
            nextPosition = currPosition + 1;
        } else {
            nextPosition = currPosition - 1;
        }

//        滑动动画
        if (nextPosition >= 0 && nextPosition < 4) {
            mViews.get(currPosition).setAlpha(1 - progress);
            mViews.get(nextPosition).setAlpha(progress);

            mViews.get(currPosition).setScaleX(1 - (progress / 2));
            mViews.get(currPosition).setScaleY(1 - (progress / 2));
            mViews.get(nextPosition).setScaleX(0.5F + progress / 2);
            mViews.get(nextPosition).setScaleY(0.5F + progress / 2);
        }

        switch (nextPosition) {
            case 0:
                mTv_home.setAlpha(progress);
                mTv_home_selected.setAlpha(1 - progress);
                mIv_home.setAlpha(progress);
                mIv_home_selected.setAlpha(1 - progress);

                mTv_message.setAlpha(1 - progress);
                mTv_message_selected.setAlpha(progress);
                mIv_message.setAlpha(1 - progress);
                mIv_message_selected.setAlpha(progress);
                break;
            case 1:
                mTv_message_selected.setAlpha(1 - progress);
                mTv_message.setAlpha(progress);
                mIv_message_selected.setAlpha(1 - progress);
                mIv_message.setAlpha(progress);
                if (currPosition == 0) {
                    mTv_home_selected.setAlpha(progress);
                    mTv_home.setAlpha(1 - progress);
                    mIv_home_selected.setAlpha(progress);
                    mIv_home.setAlpha(1 - progress);
                } else {
                    mTv_explore_selected.setAlpha(progress);
                    mTv_explore.setAlpha(1 - progress);
                    mIv_explore_selected.setAlpha(progress);
                    mIv_explore.setAlpha(1 - progress);
                }
                break;
            case 2:
                mTv_explore.setAlpha(progress);
                mTv_explore_selected.setAlpha(1 - progress);
                mIv_explore.setAlpha(progress);
                mIv_explore_selected.setAlpha(1 - progress);
                if (currPosition == 1) {
                    mTv_message.setAlpha(1 - progress);
                    mTv_message_selected.setAlpha(progress);
                    mIv_message.setAlpha(1 - progress);
                    mIv_message_selected.setAlpha(progress);
                } else {
                    mTv_mine_selected.setAlpha(progress);
                    mTv_mine.setAlpha(1 - progress);
                    mIv_mine_selected.setAlpha(progress);
                    mIv_mine.setAlpha(1 - progress);
                }
                break;
            case 3:
                mTv_mine.setAlpha(progress);
                mTv_mine_selected.setAlpha(1 - progress);
                mIv_mine.setAlpha(progress);
                mIv_mine_selected.setAlpha(1 - progress);

                mTv_explore_selected.setAlpha(progress);
                mTv_explore.setAlpha(1 - progress);
                mIv_explore_selected.setAlpha(progress);
                mIv_explore.setAlpha(1 - progress);
                break;
        }

    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                ToastUtil.showToast(this, "Search");
                break;
            case R.id.share:
                ToastUtil.showToast(this, "share");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        // 设置显示为标准模式, 还有NAVIGATION_MODE_LIST列表模式, NAVIGATION_MODE_TABS选项卡模式.
        // 参见ApiDemos
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        // 设置显示标题
        actionBar.setDisplayShowTitleEnabled(true);
        // 设置标题
        actionBar.setTitle(mTitle);

        // 1.通过设置自定义内容VIew
        // actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
        // actionBar.setDisplayShowHomeEnabled(true);
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // View view = View.inflate(this, R.layout.layout_actionbar, null);
        // actionBar.setCustomView(view);
    }


    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_home:
                mCotentPager.setCurrentItem(0);
                break;
            case R.id.bottom_mess:
                mCotentPager.setCurrentItem(1);
                break;
            case R.id.bottom_explore:
                mCotentPager.setCurrentItem(2);
                break;
            case R.id.bottom_mime:
                mCotentPager.setCurrentItem(3);
                break;
        }
    }

}
