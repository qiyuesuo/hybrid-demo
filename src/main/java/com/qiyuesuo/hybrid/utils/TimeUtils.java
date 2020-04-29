package com.qiyuesuo.hybrid.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {
	public static final String STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	
	public static Date parse(String dateStr) throws ParseException {
		if(StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_PATTERN);
		return simpleDateFormat.parse(dateStr);
	}
	
	
	public static String format(Date date, String pattern) {
		date = date == null ? new Date() : date;
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	/**
	 * 所在周的开始
	 * @param date
	 * @return
	 */
	public static Date startOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 所在周的结束
	 * @param date
	 * @return
	 */
	public static Date endOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(endOfWeek(new Date()));
	}
	
	/**
	 * 所在月的开始
	 * @param date
	 * @return
	 */
	public static Date startOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 所在月的结束
	 * @param date
	 * @return
	 */
	public static Date endOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
	 * 本月开始
	 * @return
	 */
	public static Date startOfCurMonth() {
		return startOfMonth(new Date());
	}
	
	/**
	 * 本月结束
	 * @return
	 */
	public static Date endOfCurMonth() {
		return endOfMonth(new Date());
	}
	
	/**
	 * 上月开始
	 * @return
	 */
	public static Date startOfPreMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startOfCurMonth());
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 上月结束
	 * @return
	 */
	public static Date endOfPreMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endOfCurMonth());
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 本周开始
	 * @return
	 */
	public static Date startOfCurWeek() {
		return startOfWeek(new Date());
	}
	
	/**
	 * 本周结束
	 * @return
	 */
	public static Date endOfCurWeek() {
		return endOfWeek(new Date());
	}
	
	/**
	 * 上周开始
	 * @return
	 */
	public static Date startOfPreWeek() {
		Date curMon = startOfCurWeek();
		Calendar c = Calendar.getInstance();
		c.setTime(curMon);
		c.add(Calendar.DAY_OF_WEEK, -7);
		return c.getTime();
	}
	
	/**
	 * 上周结束
	 * @return
	 */
	public static Date endOfPreWeek() {
		Date curSun = endOfCurWeek();
		Calendar c = Calendar.getInstance();
		c.setTime(curSun);
		c.add(Calendar.DAY_OF_WEEK, -7);
		return c.getTime();
	}
	
	/**
	 * 一天的开始
	 * @param date
	 * @return
	 */
	public static Date endOf(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 一天的结束
	 * @param date
	 * @return
	 */
	public static Date startOf(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 昨天的开始
	 * @return
	 */
	public static Date startOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return startOf(calendar.getTime());
	}
	
	/**
	 * 昨天的结束
	 * @return
	 */
	public static Date endOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return endOf(calendar.getTime());
	}
	
	/**
	 * 获取几天之后的日期
	 * 
	 * @param date
	 * @param after
	 * @return
	 */
	public static Date after(Date date, int after) {
		if (date == null) {
			return null;
		}
		if (after <= 0) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, after);
		return calendar.getTime();
	}

	/**
	 * 获取几天之前的日期
	 * 
	 * @param date
	 * @param before
	 * @return
	 */
	public static Date before(Date date, int before) {
		if (date == null) {
			return null;
		}
		if (before <= 0) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1 * before);
		return calendar.getTime();
	}

	public static Date startOfHour(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date endOfHour(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 获取两个时间相差的月数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthSpace(Date date1,Date date2)
	{
		   int iMonth = 0;     
	       int flag = 0;     
	       try{     
	           Calendar objCalendarDate1 = Calendar.getInstance();     
	           objCalendarDate1.setTime(date1);     
	    
	           Calendar objCalendarDate2 = Calendar.getInstance();     
	           objCalendarDate2.setTime(date2);     
	    
	           if (objCalendarDate2.equals(objCalendarDate1)) {
				return 0;
			}     
	           if (objCalendarDate1.after(objCalendarDate2)){     
	               Calendar temp = objCalendarDate1;     
	               objCalendarDate1 = objCalendarDate2;     
	               objCalendarDate2 = temp;     
	           }     
	           if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH)) {
				flag = 1;
			}     
	    
	           if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)) {
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))     
	                       * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)     
	                       - objCalendarDate1.get(Calendar.MONTH);
			} else {
				iMonth = objCalendarDate2.get(Calendar.MONTH)     
	                       - objCalendarDate1.get(Calendar.MONTH) - flag;
			}     
	    
	       } catch (Exception e){     
	        e.printStackTrace();     
	       }     
	       return iMonth;     
	}
}
