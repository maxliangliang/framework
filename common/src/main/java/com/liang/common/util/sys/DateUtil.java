package com.liang.common.util.sys;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static final String YEARMMDD_FORMAT = "yyyy-MM-dd";
	public static final String YEARMMDD_HHMMSS_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String YEARMMDDHHMMSS_FORMAT = "yyyyMMddHHmmss";
	public static final String YEARMMDD_DATE_FORMAT = "yyyyMMdd";
	public static final String HHMMSS_FORMAT = "HH:mm:ss";
	public static final String CHINA_YEARMMDD = "yyyy年MM月dd日";
	public static final SimpleDateFormat YEARMMDD_HHMMSS = new SimpleDateFormat(YEARMMDD_HHMMSS_FORMAT);
	public static final SimpleDateFormat YEARMMDD = new SimpleDateFormat(YEARMMDD_FORMAT);
	public static final SimpleDateFormat YEARMMDDHHMMSS = new SimpleDateFormat(YEARMMDDHHMMSS_FORMAT);
	public static final SimpleDateFormat YEARMMDD_YMD = new SimpleDateFormat(CHINA_YEARMMDD);
	public static final SimpleDateFormat YEARMMDDATA = new SimpleDateFormat(YEARMMDD_DATE_FORMAT);

	public static final SimpleDateFormat CHINESE_FORMAT = new SimpleDateFormat("MM月dd日 kk点mm分");
	public static final SimpleDateFormat CHINESE_FORMAT_YEAR = new SimpleDateFormat("yyyy年MM月dd日 kk点mm分");
	public static final Calendar CALENDAR = Calendar.getInstance();

	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;

	public static synchronized Date createDate(final int year, final int month, final int day, final int hour,
			final int minute, final int second, final int millisecond) {
		CALENDAR.clear();
		CALENDAR.set(year, month - 1, day, hour, minute, second);
		CALENDAR.set(Calendar.MILLISECOND, millisecond);
		return CALENDAR.getTime();
	}

	public static synchronized Date createDate(final int year, final int month, final int day) {
		createDate(year, month, day, 0, 0, 0, 0);
		return CALENDAR.getTime();
	}

	public static synchronized Date createDate(final int year, final int month, final int day, final int hour) {
		createDate(year, month, day, hour, 0, 0, 0);
		return CALENDAR.getTime();
	}

	public static synchronized Date createDate(final int year, final int month, final int day, final int hour,
			final int minute) {
		createDate(year, month, day, hour, minute, 0, 0);
		return CALENDAR.getTime();
	}

	public static synchronized Date createDate(final int year, final int month, final int day, final int hour,
			final int minute, final int second) {
		createDate(year, month, day, hour, minute, second, 0);
		return CALENDAR.getTime();
	}

	/**
	 * 判断是否在两个时间段之间 比较时间格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param startTime
	 *            开始时间
	 * @param nowTime
	 *            比较时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static boolean isExchange(String startTime, String nowTime, String endTime) {
		if (startTime == null || endTime == null)
			return false;
		Date startDate = null;
		Date endDate = null;
		Date nowDate = null;
		try
		{
			startDate = YEARMMDD_HHMMSS.parse(startTime);
			endDate = YEARMMDD_HHMMSS.parse(endTime);
			if (nowTime == null || "".equals(nowTime))
			{
				nowDate = getNowDate();
			}
			else
			{
				nowDate = YEARMMDD_HHMMSS.parse(nowTime);
			}
		}
		catch (Exception e)
		{
		}
		return nowDate.after(startDate) && nowDate.before(endDate);
	}

	/**
	 * 判断当前时间在两个时间之内 与当前时间相比
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static boolean isExchange(String startTime, String endTime) {
		return isExchange(startTime, null, endTime);
	}

	/**
	 * 判断时间是否是当天
	 * 
	 * @param startTime
	 * @return
	 */
	public static boolean isEqual(String startTime, String nowTime) {
		Date startDate = null;
		Date nowDate = null;
		try
		{
			startDate = YEARMMDD.parse(startTime);
			if (nowTime == null || "".equals(nowTime))
			{
				nowDate = getNowDateShort();
			}
			else
			{
				nowDate = YEARMMDD.parse(nowTime);
			}
		}
		catch (Exception e)
		{
		}
		return startDate.compareTo(nowDate) == 0 ? true : false;
	}

	public static boolean isEqual(String startTime) {
		return isEqual(startTime, null);
	}

	/**
	 * 判断是否是同一天
	 * 
	 * @param startTime
	 *            yyyy-MM-dd
	 * @param endTime
	 *            yyyy-MM-dd
	 * @return
	 */
	public static boolean isSameToday(Date startTime, Date endTime) {
		if (startTime == null)
			return false;
		try
		{
			String startTimeStr = YEARMMDD.format(startTime);
			String endTimeStr = "";
			if (endTime == null)
			{
				endTimeStr = getNowStrShort();
			}
			else
			{
				endTimeStr = YEARMMDD.format(endTime);
			}
			return startTimeStr.equals(endTimeStr) ? true : false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean isSameToday(Date startTime) {
		return isSameToday(startTime, null);
	}

	public static String chineseMD() {
		Date now = new Date();
		return CHINESE_FORMAT.format(now);
	}

	public static String chineseYMD() {
		Date now = new Date();
		return CHINESE_FORMAT_YEAR.format(now);
	}

	/**
	 * 获取现在时间
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		String dateString = YEARMMDD.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = YEARMMDD.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static String getNowStrShort() {
		Date currentTime = new Date();
		String dateString = YEARMMDD.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当日
	 * 
	 * @return 返回时间格式yyyyMMdd
	 */
	public static String getToday() {
		Date currentTime = new Date();
		String dateString = YEARMMDDATA.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		return currentTime;
	}

	public static Date getDate(String timeStr) {
		try
		{
			return YEARMMDDHHMMSS.parse(timeStr);
		}
		catch (Exception e)
		{
		}
		return new Date();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowStrDate() {
		Date currentTime = new Date();
		String dateString = YEARMMDD_HHMMSS.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return返回短时间格式 yyyy年MM月dd日
	 */
	public static String getNowYMD() {
		Date currentTime = new Date();
		String dateString = YEARMMDD_YMD.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return返回短时间格式 yyyyMMddHHmmss
	 */
	public static String getNowYDate() {
		Date currentTime = new Date();
		String dateString = YEARMMDDHHMMSS.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前的小时
	 * 
	 * @return
	 */
	public static int getNowHour() {
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		return hour;
	}

	/**
	 * 获取当前的月份中的天数
	 * 
	 * @return
	 */
	public static int getNowDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.DAY_OF_MONTH);// 小时
		return hour;
	}

	/**
	 * 获取当前的月份
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.MONTH);// 月份
		return hour;
	}

	/**
	 * 获取当前的年份
	 * 
	 * @return
	 */
	public static int getNowYear() {
		Calendar ca = Calendar.getInstance();
		int hour = ca.get(Calendar.YEAR);// 年份
		return hour;
	}

	/**
	 * 获取当前的年的第几周
	 * 
	 * @return
	 */
	public static int getNowWeek() {
		Calendar ca = Calendar.getInstance();
		int week = ca.get(Calendar.WEEK_OF_YEAR);// 年中第几周
		return week;
	}

	/**
	 * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1
	 */
	public static Date getDateBeforeMonth(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -number);
		return cal.getTime();
	}

	/**
	 * 根据格式得到格式化后的日期
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @see SimpleDateFormat#parse(String)
	 * @return Date 返回格式化后的日期，格式由参数<code>format</code>
	 *         定义，如yyyy-MM-dd，如2006-02-15
	 */
	public static Date getFormatDate(String currDate, String format) {
		if (currDate == null)
		{
			return null;
		}
		SimpleDateFormat dtFormatdB = null;
		try
		{
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.parse(currDate);
		}
		catch (Exception e)
		{
			dtFormatdB = new SimpleDateFormat(YEARMMDD_FORMAT);
			try
			{
				return dtFormatdB.parse(currDate);
			}
			catch (Exception ex)
			{
			}
		}
		return null;
	}

	/**
	 * 根据格式得到格式化后的时间
	 * 
	 * @param currDate
	 *            要格式化的时间
	 * @param format
	 *            时间格式，如yyyy-MM-dd HH:mm:ss
	 * @see SimpleDateFormat#format(Date)
	 * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDateTime(Date currDate, String format) {
		if (currDate == null)
		{
			return "";
		}
		SimpleDateFormat dtFormatdB = null;
		try
		{
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.format(currDate);
		}
		catch (Exception e)
		{
			dtFormatdB = new SimpleDateFormat(YEARMMDD_HHMMSS_FORMAT);
			try
			{
				return dtFormatdB.format(currDate);
			}
			catch (Exception ex)
			{
			}
		}
		return "";
	}

	/**
	 * 得到格式化后的日，格式为yyyyMMdd，如20060210
	 * 
	 * @param currDate
	 *            要格式化的日期
	 * @return String 返回格式化后的日，格式为yyyyMMdd，如20060210
	 */
	public static String getFormatDay(Date currDate) {
		return getFormatDateTime(currDate, YEARMMDD_DATE_FORMAT);
	}

	/**
	 * 返回年月日格式的日期
	 * 
	 * @param currDate
	 * @return
	 */
	public static String getFormatDayWithyyyyMMdd(Date currDate) {
		return getFormatDateTime(currDate, YEARMMDD_FORMAT);
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 获得当前日期的整数型
	 * 
	 * @return
	 * @author Liu zhilai Aug 25, 2014 String
	 */
	public static int getDateInt() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sf.format(date));
	}

	/**
	 * 查询车险保单专用 将XMLGregorianCalendar转换为Date然后格式化输出
	 * 
	 * @param cal
	 * @return date
	 */
	public static String xmlDate2Date(XMLGregorianCalendar cal) {
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		if (cal != null)
		{
			date = sdf0.format(cal.toGregorianCalendar().getTime());
		}

		return date;
	}

	/**
	 * 删除给定Date的时分秒毫秒
	 * 
	 * @param now
	 * @return
	 */
	public static Date truncateTimeOfDate(Date now) {
		if (now == null)
		{
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(smdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 日期格式相互转换
	 * 
	 * @param date
	 *            字符串日期
	 * @param fFormat
	 *            目前的日期格式
	 * @param tFormat
	 *            要转换成的格式
	 * @return 返回转换后的日期
	 * @throws ParseException
	 */
	public static String dateFormat(String date, String fFormat, String tFormat) throws ParseException {
		SimpleDateFormat fd = new SimpleDateFormat(fFormat);
		SimpleDateFormat td = new SimpleDateFormat(tFormat);
		Date d = fd.parse(date);
		return td.format(d);
	}

	/**
	 * 获取现在时间
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 两个时间之间的天数
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try
		{
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		}
		catch (Exception e)
		{
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return Math.abs(day);
	}
	/*
	 * public static void main(String[] args) { long day=getDays("1986-04-13",
	 * getStringDate()); long year=day/365; long month=(day-365L*year)/30+1;
	 * long week=day/7+1; System.out.println(year+"岁"+month+"月"+week+"周"); }
	 */

	/**
	 * 根据传入type类型，获取两个时间之间的跨度（start-end）
	 * 
	 * @author 孙叙社
	 * @version 1.0
	 * @param start
	 * @param end
	 * @param type
	 * @return
	 */
	public static int dateDiff(Date start, Date end, int type) {
		int diff = 0;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		// 年份
		int cy1 = c1.get(Calendar.YEAR);
		int cy2 = c2.get(Calendar.YEAR);
		// 月份
		int cm1 = c1.get(Calendar.MONTH);
		int cm2 = c2.get(Calendar.MONTH);
		// 日
		int cd1 = c1.get(Calendar.DATE);
		int cd2 = c2.get(Calendar.DATE);

		// 年份
		if (cm1 - cm2 == 0)
			diff = cy1 - cy2 + (cd1 - cd2 >= 0 ? 0 : -1);
		else if (cm1 - cm2 > 0)
			diff = cy1 - cy2;
		else diff = cy1 - cy2 - 1;

		switch (type)
		{
		case MONTH:
			diff = diff * 12 + cm1 - cm2 + (cd1 - cd2 < 0 ? -1 : 0);
			break;
		case DAY:
			diff = (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 24 / 60 / 60 / 1000);
			break;
		}
		return diff;
	}
	
	/**
	 * 获得指定日期所在周的开始日期
	 * @author  wangxq
	 * @date    2016年11月20日
	 */
	public static Date getWeekStart(Date date){
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
	    currentDate.setFirstDayOfWeek(Calendar.MONDAY);            
	    currentDate.set(Calendar.HOUR_OF_DAY, 0);  
	    currentDate.set(Calendar.MINUTE, 0);  
	    currentDate.set(Calendar.SECOND, 0);  
	    currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    return currentDate.getTime();
	}
	
	/**
	 * 获得指定日期所在月的第一天
	 * @author  wangxq
	 * @date    2016年11月21日
	 */
	public static Date getMonthStart(Date date){
		//获取当前月第一天：
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		//设置为1号,当前日期既为本月第一天 
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}
	
	/**
	 * 获得指定日期所在月的第一天
	 * @author  wangxq
	 * @date    2016年11月21日
	 */
	public static Date getMonthStart(String date){
		//获取当前月第一天：
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(YEARMMDD.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.MONTH, 0);
		//设置为1号,当前日期既为本月第一天 
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}
	
	/**
	 * description 求两个日期之间的工作日（除去周六日）
	 * @author     qingui
	 * @date       2016年12月2日 下午5:44:02
	 */
	@SuppressWarnings("deprecation")
	public static int getDutyDays(String strStartDate,String strEndDate) {  
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		Date startDate=null;  
		Date endDate = null;  
		  
		try {  
			startDate=df.parse(strStartDate);  
			endDate = df.parse(strEndDate);  
		} catch (ParseException e) {  
			System.out.println("非法的日期格式,无法进行转换");  
			e.printStackTrace();  
		}
		int result = 0;  
		while (startDate.compareTo(endDate) <= 0) {  
			if (startDate.getDay() != 6 && startDate.getDay() != 0)  
			result++;  
			startDate.setDate(startDate.getDate() + 1);  
		}  
		  
		return result;  
	}  
}