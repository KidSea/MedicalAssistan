package com.example.yuxuehai.medicalassistan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.ui.LoginActivity;
import com.example.yuxuehai.medicalassistan.ui.SettingsActivity;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;

import cn.bmob.v3.BmobUser;

/**
 * Created by yuxuehai on 17-2-17.
 */

public class NavigationDrawerFragment extends BaseFragment implements View.OnClickListener {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private DrawerLayout mDrawerLayout;
    private View mDrawerListView;
    private View mFragmentContainerView;
    private LinearLayout mMenu_item_quests;
    private LinearLayout mMenu_item_blog;
    private LinearLayout mMenu_item_rss;
    private LinearLayout mMenu_item_setting;
    private LinearLayout mMenu_item_exit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState
                    .getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDrawerListView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);


        return mDrawerListView;
    }

    @Override
    protected void initView(View rootView) {

        mMenu_item_quests = (LinearLayout) rootView.findViewById(R.id.menu_item_quests);
        mMenu_item_blog = (LinearLayout) rootView.findViewById(R.id.menu_item_blog);
        mMenu_item_rss = (LinearLayout) rootView.findViewById(R.id.menu_item_rss);
        mMenu_item_setting = (LinearLayout) rootView.findViewById(R.id.menu_item_setting);
        mMenu_item_exit = (LinearLayout) rootView.findViewById(R.id.menu_item_exit);

        mDrawerListView.setOnClickListener(this);
        mMenu_item_quests.setOnClickListener(this);
        mMenu_item_blog.setOnClickListener(this);
        mMenu_item_rss.setOnClickListener(this);
        mMenu_item_setting.setOnClickListener(this);
        mMenu_item_exit.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.menu_item_quests:
                break;
            case R.id.menu_item_blog:
                break;
            case R.id.menu_item_rss:
                break;
            case R.id.menu_item_setting:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
            case R.id.menu_item_exit:
                onExitUser();
                break;
            default:
                break;

        }
        mDrawerLayout.postDelayed(() -> mDrawerLayout.closeDrawers(), 800);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null
                && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation
     * drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer
        // opens
        // 给DrawerLayout设置阴影
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        // set up the drawer's list view with items and click listener


        // 创建自定义返回按钮内容
        drawerArrow = new DrawerArrowDrawable(getActivity()) {
            public boolean isLayoutRtl() {
                return false;
            }
        };

        // 创建v7包下的返回按钮ActionBarDrawerToggle, (封装DrawerArrowDrawable)
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                // 同步开关状态
                mDrawerToggle.syncState();
            }
        });

        // 设置按钮监听, 点击之后, 或者拖拽过程中返回按钮可以实时更新其动画位置.
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void openDrawerMenu() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    "Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }


    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }

    private void onExitUser() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        RelativeLayout inflate = (RelativeLayout) inflater.inflate(R.layout.dialog_exit, null);

        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(inflate); // 修改整个dialog窗口的显示

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清除缓存对象
                UserBean.logOut();
                SharePrefUtil.setBoolean(getActivity(), Constants.IsLogin, false);
                // 现在的currentUser是null了
                BmobUser currentUser = UserBean.getCurrentUser();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

    }
}
