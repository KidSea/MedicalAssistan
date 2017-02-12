package com.example.yuxuehai.medicalassistan.utlis;

import java.io.Closeable;
import java.io.IOException;

/**
 * Io utils
 * @author yuxuehai
 */
public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}
}
