package com.example.yuxuehai.medicalassistan;

import android.support.test.runner.AndroidJUnit4;

import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.Ward;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        List<BmobObject> patiens = new ArrayList<>();
        Ward ward = new Ward();
        ward.setObjectId("24681a402a");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Patient patient = new Patient();
            patient.setPatientName("病人"+ random.nextInt(20));
            patient.setAge(20+i*3);
            patient.setGender("男");
            patient.setPhoneNum("1581942912"+i);
            patient.setNum(""+i);
            patient.setPathology("阑尾炎");
            patient.setDetail("该患者腹痛右下腹部疼痛,患者常有轻重不等的消化不良、食欲下降," +
                    "确诊为急性阑尾炎,住院疗养");
            patient.setWard(ward);
            patiens.add(patient);
        }
        new BmobBatch().insertBatch(patiens).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {

            }
        });
    }
}
