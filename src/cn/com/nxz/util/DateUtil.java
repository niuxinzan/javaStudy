package cn.com.nxz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author cennavi
 * todo:由于simpledateformat是线程不安全的，所以需要定义个timeuitl类，保证线程安全
 */
public class DateUtil {  
	  
    private static final ThreadLocal<DateFormat> messageFormat = new ThreadLocal<DateFormat>();  
    private static final String MESSAGE_FORMAT = "yyyyMMdd HH:mm:ss.ms";  
  
    private static final DateFormat getDateFormat() {  
        DateFormat format = messageFormat.get();  
        if (format == null) {  
            format = new SimpleDateFormat(MESSAGE_FORMAT, Locale.getDefault());  
            messageFormat.set(format);  
        }  
  
        return format;  
    }  
    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }  
}  
