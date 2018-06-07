package com.example.yuxuehai.medicalassistan.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseFragment;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.presenter.impl.MinePresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.ui.SettingsActivity;
import com.example.yuxuehai.medicalassistan.utlis.LogUtils;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.MineFragmentView;

import java.io.File;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.yuxuehai.medicalassistan.utlis.Constants.CAMERA_REQUEST_CODE;
import static com.example.yuxuehai.medicalassistan.utlis.Constants.IMAGE_REQUEST_CODE;
import static com.example.yuxuehai.medicalassistan.utlis.Constants.PHOTO_IMAGE_FILE_NAME;
import static com.example.yuxuehai.medicalassistan.utlis.Constants.REQUEST_BLUETOOTH_PERMISSION;
import static com.example.yuxuehai.medicalassistan.utlis.Constants.RESULT_REQUEST_CODE;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener,MineFragmentView{

    private LinearLayout mSettings;
    private AlertDialog mPhotoDialog;
    private CircleImageView mIv_mine;
    private TextView mTv_phone;
    private TextView mTv_des;
    private File tempFile;

    private MinePresenterDaoImpl mMinePresenterDao;


    public <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }


    @Override
    public void showDialog() {
        mPhotoDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
        mPhotoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPhotoDialog.show();

        Window window = mPhotoDialog.getWindow();
        window.setContentView(R.layout.dialog_photo); // 修改整个dialog窗口的显示
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = mPhotoDialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = dm.widthPixels;
        mPhotoDialog.getWindow().setAttributes(lp); // 设置宽度

        mPhotoDialog.findViewById(R.id.btn_camera).setOnClickListener(this);
        mPhotoDialog.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mPhotoDialog.findViewById(R.id.btn_picture).setOnClickListener(this);

    }

    @Override
    public void jumptoCamera() {
        requestWESPermission(); // 安卓6.0以上需要申请权限
        mPhotoDialog.dismiss();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 调用系统的拍照功能
        if (Build.VERSION.SDK_INT >= 24) {
            File file = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
            // 判断内存卡是否可用，可用的话就进行储存
            Uri uriForFile = FileProvider.getUriForFile(getContext(),
                    getActivity().getPackageName() + ".fileprovider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    uriForFile);
        } else {
            // 判断内存卡是否可用，可用的话就进行储存
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }


    @Override
    public void jumptoGallery() {
        mPhotoDialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    public void setIcon(Bitmap bitmap) {
        mIv_mine.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_settings:
                getActivity().startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.iv_mine:
                showDialog();
                break;
            case R.id.btn_camera:
                jumptoCamera();
                break;
            case R.id.btn_picture:
                jumptoGallery();
                break;
            case R.id.btn_cancel:
                mPhotoDialog.dismiss();
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(BmobUser.getCurrentUser() != null){
            //System.out.println(UserBean.getCurrentUser());
            UserBean userEntity = BmobUser.getCurrentUser(UserBean.class);
            //System.out.println(userEntity);
            if(userEntity.getPhoto() != null){
                Glide.with(getActivity()).load(userEntity.getPhoto().getFileUrl()).into(mIv_mine);
            }
            mTv_phone.setText(userEntity.getMobilePhoneNumber());
            mTv_des.setText(userEntity.getUsername()+"/"+userEntity.getHospital());
        }
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_mine, null);
        return view;
    }


    @Override
    protected void initView(View rootView) {
        mIv_mine = $(rootView, R.id.iv_mine);
        mTv_phone = $(rootView, R.id.tv_phone_num);
        mTv_des = $(rootView, R.id.tv_des);
        mSettings = $(rootView, R.id.rl_settings);

        mSettings.setOnClickListener(this);
        mIv_mine.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mMinePresenterDao = new MinePresenterDaoImpl(getContext(),this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case IMAGE_REQUEST_CODE: // 相册数据
                if (data != null){
                    startPhotoZoom(data.getData());
                }
                break;
            case CAMERA_REQUEST_CODE: // 相机数据
                tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                if (Build.VERSION.SDK_INT >= 24) {
                    Uri uriForFile = FileProvider.getUriForFile(getContext(),
                            getActivity().getPackageName() + ".fileprovider", tempFile);
                    startPhotoZoom(uriForFile);
                } else {
                    startPhotoZoom(Uri.fromFile(tempFile));
                }
                break;
            case RESULT_REQUEST_CODE: // 有可能点击舍弃
                if (data != null) {
                    // 拿到图片设置, 然后需要删除tempFile
                    mMinePresenterDao.setImageToView(data, tempFile);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void requestWESPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                // 判断是否需要 向用户解释，为什么要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    ToastUtil.showShort(getActivity(),"Need write external storage permission.");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.
                        WRITE_EXTERNAL_STORAGE}, REQUEST_BLUETOOTH_PERMISSION);
                return;
            } else {
            }
        } else {
        }
    }

    /**
     * 裁剪
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            LogUtils.e("裁剪uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // 裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        // 发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }



}
