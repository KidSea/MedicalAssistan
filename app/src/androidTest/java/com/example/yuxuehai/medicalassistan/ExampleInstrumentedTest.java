package com.example.yuxuehai.medicalassistan;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.yuxuehai.medicalassistan.bean.Ward;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Ward ward = new Ward();
        ward.setRoomName("501");
        ward.setPatientNum(5);
        ward.setLocation("主楼-胃肠科-501");
        ward.setCategory("胃肠科");
        ward.setDoctor("李四");

        ward.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Log.i("bmob","创建数据成功：" + s);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
