package Spider.Middle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
@author Griezmann
@version 2019年2月2日下午3:38:34
*/
public class DateUtil {
	public static final String pattern="yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat sdf=new SimpleDateFormat(pattern);
	//字符串转date
	public static Date formatStringToDate(String dateTimeString) throws ParseException {
		Date datetimeObj=sdf.parse(dateTimeString);
		return datetimeObj;
	}
	//date转字符串
	public static String formatDateToString(Date currentDate) { 
		return sdf.format(currentDate);
	}
	//获取当前时间
	public static Date getDate() {
		return new Date();
	}
	public static void main(String[] args) throws ParseException {
		String dateTimeString="2019-02-02 12:27:00";
		System.out.println(formatStringToDate(dateTimeString));
		System.out.println(getDate());
	
	}
}
