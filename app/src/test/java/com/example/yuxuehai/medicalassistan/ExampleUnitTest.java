package com.example.yuxuehai.medicalassistan;

import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.Ward;

import org.junit.Test;

import java.util.Random;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Ward ward = new Ward();
        ward.setObjectId("24681a402a");
        Random random = new Random();
        Patient patient = new Patient();
        patient.setPatientName("病人"+ random.nextInt(20));
        patient.setAge(20+random.nextInt(5));
        patient.setGender("男");
        patient.setPhoneNum("1581942912");
        patient.setNum("5");
        patient.setPathology("阑尾炎");
        patient.setDetail("该患者腹痛右下腹部疼痛,患者常有轻重不等的消化不良、食欲下降," +
                "确诊为急性阑尾炎,住院疗养");
        patient.setWard(ward);
        patient.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });

    }
}