package com.example.yuxuehai.medicalassistan.ui;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.AppManager;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyFrgmentAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.fragment.FragmentFactory;
import com.example.yuxuehai.medicalassistan.fragment.NavigationDrawerFragment;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.widget.QuickOptionDialog;

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
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.
        NavigationDrawerCallbacks, View.OnClickListener {

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
    private ImageView mToolBar_selectAll;
    private ImageView mToolBar_delete;
    private TextView mTv_home;
    private TextView mTv_home_selected;
    private TextView mTv_message;
    private TextView mTv_explore;
    private TextView mTv_message_selected;
    private TextView mTv_explore_selected;
    private TextView mTv_mine;
    private TextView mTv_mine_selected;

    private ArrayList<BaseFragment> mViews;

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
        mToolBar_selectAll = (ImageView) findViewById(R.id.toolbar_selectall);
        mToolBar_delete = (ImageView) findViewById(R.id.toolbar_delete);

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
        AppManager.getAppManager().addActivity(MainActivity.this);
        SharePrefUtil.setBoolean(this, Constants.IsLogin, true);


        mViews = new ArrayList<>();

        mViews.add(FragmentFactory.getFragment(0));
        mViews.add(FragmentFactory.getFragment(1));
        mViews.add(FragmentFactory.getFragment(2));
        mViews.add(FragmentFactory.getFragment(3));

        mCotentPager.setAdapter(new MyFrgmentAdapter(getSupportFragmentManager(), mViews));

        mCotentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
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

        mCotentPager.setCurrentItem(3);
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
                setselectedAlpha(mIv_home_selected, mIv_home, mTv_home_selected, mTv_home);
            } else if (mIv_home_selected.getAlpha() == 1) {
                setunselectedAlpha(mIv_home_selected, mIv_home, mTv_home_selected, mTv_home);
            }
        } else if (i == 1) {
            if (mIv_message_selected.getAlpha() == 0) {
                setselectedAlpha(mIv_message_selected, mIv_message,
                        mTv_message_selected, mTv_message);
            } else if (mIv_message_selected.getAlpha() == 1) {
                setunselectedAlpha(mIv_message_selected, mIv_message,
                        mTv_message_selected, mTv_message);
            }
        } else if (i == 2) {
            if (mIv_explore_selected.getAlpha() == 0) {
                setselectedAlpha(mIv_explore_selected, mIv_explore,
                        mTv_explore_selected, mTv_explore);
            } else if (mIv_explore_selected.getAlpha() == 1) {
                setunselectedAlpha(mIv_explore_selected, mIv_explore,
                        mTv_explore_selected, mTv_explore);
            }
        } else if (i == 3) {
            if (mIv_mine_selected.getAlpha() == 0) {
                setselectedAlpha(mIv_mine_selected, mIv_mine, mTv_mine_selected, mTv_mine);
            } else if (mIv_mine_selected.getAlpha() == 1) {
                setunselectedAlpha(mIv_mine_selected, mIv_mine, mTv_mine_selected, mTv_mine);
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

        switch (nextPosition) {
            case 0:
                setAlphaProgress(mIv_home_selected, mIv_home, mTv_home_selected, mTv_home,progress);

                setunAlphaProgress(mIv_message_selected, mIv_message,
                        mTv_message_selected, mTv_message,progress);
                break;
            case 1:
                setAlphaProgress(mIv_message_selected, mIv_message,
                        mTv_message_selected, mTv_message,progress);
                if (currPosition == 0) {
                    setunAlphaProgress(mIv_home_selected, mIv_home,
                            mTv_home_selected, mTv_home,progress);
                } else {
                    setunAlphaProgress(mIv_explore_selected, mIv_explore,
                            mTv_explore_selected, mTv_explore,progress);
                }
                break;
            case 2:
                setAlphaProgress(mIv_explore_selected, mIv_explore,
                        mTv_explore_selected, mTv_explore,progress);
                if (currPosition == 1) {
                    setunAlphaProgress(mIv_message_selected, mIv_message,
                            mTv_message_selected, mTv_message,progress);
                } else {
                    setunAlphaProgress(mIv_mine_selected, mIv_mine,
                            mTv_mine_selected, mTv_mine,progress);
                }
                break;
            case 3:
                setAlphaProgress(mIv_mine_selected, mIv_mine, mTv_mine_selected, mTv_mine,progress);
                setunAlphaProgress(mIv_explore_selected, mIv_explore,
                        mTv_explore_selected, mTv_explore,progress);
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
        // 设置显示标题
        actionBar.setDisplayShowTitleEnabled(true);
        // 设置标题
        actionBar.setTitle(mTitle);
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
                mCotentPager.setCurrentItem(0, false);
                break;
            case R.id.bottom_mess:
                mCotentPager.setCurrentItem(1, false);
                break;
            case R.id.bottom_explore:
                mCotentPager.setCurrentItem(2, false);
                break;
            case R.id.bottom_mime:
                mCotentPager.setCurrentItem(3, false);
                break;
            case R.id.iv_add:
                showQuickOption();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }

    // 显示快速操作界面
    private void showQuickOption() {
        // TODO Auto-generated method stub
        final QuickOptionDialog dialog = new QuickOptionDialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void setselectedAlpha(ImageView im_se, ImageView im_unse,
                                  TextView tv_se, TextView tv_unse) {
        im_se.setAlpha(1.0f);
        im_unse.setAlpha(0.0f);
        tv_se.setAlpha(1.0f);
        tv_unse.setAlpha(0.0f);
    }

    private void setunselectedAlpha(ImageView im_se, ImageView im_unse,
                                    TextView tv_se, TextView tv_unse) {
        im_se.setAlpha(0.0f);
        im_unse.setAlpha(1.0f);
        tv_se.setAlpha(0.0f);
        tv_unse.setAlpha(1.0f);
    }

    private void setAlphaProgress(ImageView im_se, ImageView im_unse, TextView tv_se,
                                  TextView tv_unse, float progress) {
        tv_unse.setAlpha(progress);
        tv_se.setAlpha(1 - progress);
        im_unse.setAlpha(progress);
        im_se.setAlpha(1 - progress);
    }

    private void setunAlphaProgress(ImageView im_se, ImageView im_unse, TextView tv_se,
                                    TextView tv_unse, float progress) {
        tv_se.setAlpha(progress);
        tv_unse.setAlpha(1 - progress);
        im_se.setAlpha(progress);
        im_unse.setAlpha(1 - progress);
    }


}
