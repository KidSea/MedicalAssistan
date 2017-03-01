package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.presenter.MinePresenterDao;
import com.example.yuxuehai.medicalassistan.utlis.LogUtils;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.MineFragmentView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static com.example.yuxuehai.medicalassistan.utlis.Constants.PHOTO_IMAGE_FILE_NAME;
import static com.example.yuxuehai.medicalassistan.utlis.UIUtils.getString;

/**
 * Created by yuxuehai on 17-3-1.
 */

public class MinePresenterDaoImpl extends BasePresenter implements MinePresenterDao {

    private MineFragmentView mMineFragmentView;
    private Context mContext;

    public MinePresenterDaoImpl(Context context, MineFragmentView view) {
        super(context);
        this.mContext = context;
        this.mMineFragmentView = view;
    }


    /**
     * 设置icon并上传服务器
     * @param data
     */
    @Override
    public void setImageToView(Intent data, final File tempFile) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            final Bitmap bitmap = bundle.getParcelable("data");
            final BmobFile bmobFile = new BmobFile(bitmapToFile(bitmap,tempFile));

            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null) {
                        // 此时上传成功
                        UserBean userEntity = new UserBean();
                        userEntity.setPhoto(bmobFile);// 获取文件并赋值给实体类
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    mMineFragmentView.setIcon(bitmap);
                                    ToastUtil.showShort(mContext, mContext.getString(R.string.avatar_editor_success));
                                } else {
                                    ToastUtil.showShort(mContext, getString(R.string.avatar_editor_failure));
                                }
                            }
                        });
                    } else {
                        ToastUtil.showShort(mContext, getString(R.string.avatar_editor_failure));
                    }
                    // 既然已经设置了图片，我们原先的就应该删除
                    if (tempFile != null) {
                        tempFile.delete();
                        LogUtils.i("tempFile已删除");
                    }
                }
                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
    }

    /**
     * Bitmap转File
     */
    public File bitmapToFile(Bitmap bitmap, File tempFile) {
        tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)) {
                bos.flush();
                bos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
