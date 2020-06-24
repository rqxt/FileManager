package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static Date date = new Date();
	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(getToday());
	}
}
