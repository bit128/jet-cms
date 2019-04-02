package com.lanxin.pandora.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	
	/**
	 * 获取uniqid码
	 * @return
	 */
	public static String uniqid() {
		long timestamp = Calendar.getInstance().getTimeInMillis();
		return Long.toHexString(timestamp)
				+ Integer.toHexString((int) (16 + Math.random() * (255 - 16)));
	}
	
	/**
	 * 格式化时间戳
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public static String date(long timestamp, String format) {
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		Date dates = new Date(timestamp * 1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(dates);
	}
	
	/**
	 * 格式化当前时间
	 * @param format
	 * @return
	 */
	public static String dateNow(String format) {
		if (format == null || format.equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(calendar.getTime());
	}

}
