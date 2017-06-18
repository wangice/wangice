package com.ice.time;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 时间格式解析
 * 
 * @author ice
 * @create on 2017-06-17
 */
public class IceDate {

	public static final long SECOND = 1 * 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static final String nowTime() {
		LocalDate now = LocalDate.now();
		return now.toString();
	}

	/**
	 * 
	 * @param date
	 *            Nov 01 14:01:55 CST 2017.
	 * @return
	 */
	public static final LocalDateTime parseLocale(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd HH:mm:ss zzz yyyy", Locale.US);
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            格式yyyyMMdd
	 * @return
	 */
	public static final LocalDateTime parseDateyyyyMMdd(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            格式 yyyy-MM-dd
	 * @return
	 */
	public static final LocalDateTime parseDateyyyy_MM_dd(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            yyyyMMddHH
	 * @return
	 */
	public static final LocalDateTime parseDateyyyyMMddHH(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            格式 yyyyMMDDHHmm
	 * @return
	 */
	public static final LocalDateTime parseDateyyyyMMDDHHmm(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDDHHmm");
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            格式yyyy_MM_dd_HH_mm_dd
	 * @return
	 */
	public static final LocalDateTime parseDateyyyy_MM_dd_HH_mm_dd(String date) {
		if (date == null) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_dd");
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static final String parseDateyyyy_MM_dd(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return IceDate.parseLocalDate(formatter, date);
	}

	/**
	 * 
	 * @param localDate
	 * @return yyyy-MM
	 */
	public static final String parseDateyyyy_MM(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return IceDate.parseLocalDate(formatter, localDate);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String parseDateyyyy_MM_ddHHmmss(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return 格式 yyyy-MM-dd HH:mm
	 */
	public static final String parseDateyyyy_MM_ddHHmm(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyy/MM/dd HH:mm
	 */
	public static final String parseDateyyyyMMddHHmm2(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDate
	 * @return yyyyMMdd
	 */
	public static final String parseDateyyyyMMdd(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return IceDate.parseLocalDate(formatter, localDate);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyyMMddHH
	 */
	public static final String parseDateyyyyMMddHH(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyyMMddHHmmss
	 */
	public static final String parseDateyyyyMMddHHmmss2(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyyMMddHHmm
	 */
	public static final String parseDateyyyyMMddHHmm3(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return MMddHHmmss
	 */
	public static final String parseDateMMddHHmmss(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
		return IceDate.parseLocalDateTime(formatter, localDateTime);
	}

	/**
	 * 
	 * @param localTime
	 * @return HH:mm:ss
	 */
	public static final String parseHHmmss(LocalTime localTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return IceDate.parseLocalTime(formatter, localTime);
	}

	/**
	 * 
	 * @param localTime
	 * @return HH:mm:ss.ms
	 */
	public static final String parseHHmmssms(LocalTime localTime) {
		long ms = localTime.getNano() / 1000000;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return IceDate.parseLocalTime(formatter, localTime) + "."
				+ (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyy-MM-dd HH:mm:ss.ms
	 */
	public static final String parseDateyyyyMMddHHmmssms(LocalDateTime localDateTime) {
		long ms = localDateTime.getNano() / 1000000;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return IceDate.parseLocalDateTime(formatter, localDateTime) + "."
				+ (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
	}

	/**
	 * 
	 * @param localDateTime
	 * @return yyyyMMddHHmmssms
	 */
	public static final String parseDateyyyyMMddHHmmssms2(LocalDateTime localDateTime) {
		long ms = localDateTime.getNano() / 1000000;
		return IceDate.parseDateyyyyMMddHHmm3(localDateTime) + "."
				+ (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
	}

	/** 
	 * 
	 * 置为凌晨00:00:00 000,Calendar提供的set函数.
	 */
	public static final LocalDateTime set000000(LocalDateTime localDateTime) {
		LocalDateTime dateTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
				localDateTime.getDayOfMonth(), 0, 0, 0);
		return dateTime;
	}
	
	/**
	 * 当前时间的hour, 小于10时前面补零.
	 * @param localTime
	 * @return
	 */
	public static final String hour(LocalTime localTime) {
		int hour = localTime.getHour();
		return hour > 9 ? hour+"" :"0"+ hour;
	}
	
	public static final int yyyymmdd(LocalDate localDate) {
		return Integer.parseInt(localDate.getYear() + "" + localDate.getMonth() +""+ localDate.getDayOfMonth());
	}
	
	

	public static final String parseLocalDate(DateTimeFormatter formatter, LocalDate localDate) {
		try {
			return localDate == null ? null : localDate.format(formatter);
		} catch (Exception e) {
			return null;
		}
	}

	public static final String parseLocalTime(DateTimeFormatter formatter, LocalTime localTime) {
		try {
			return localTime == null ? null : localTime.format(formatter);
		} catch (Exception e) {
			return null;
		}
	}

	public static final String parseLocalDateTime(DateTimeFormatter formatter, LocalDateTime localDateTime) {
		try {
			return localDateTime == null ? null : localDateTime.format(formatter);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		LocalDateTime parseLocale = parseLocale("12 01 14:01:55 CST 2017");

		System.out.println(parseLocale);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime localDateTime1 = LocalDateTime.of(2017, 2, 27, 0, 0);
		String parseLocalDate = parseLocalDateTime(formatter, localDateTime1);
		System.out.println("parseLocalDate:" + parseLocalDate);

		LocalDate localDate = LocalDate.now();
		System.out.println("localDate:" + localDate);
		LocalTime localTime = LocalTime.now();
		System.out.println("localTime:" + localTime);
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("localDateTime:" + localDateTime);

		System.out.println(localTime.getNano());

		LocalDateTime set000000 = set000000(localDateTime);
		System.out.println(IceDate.parseDateyyyy_MM_ddHHmmss(set000000));
	
		
	}
}
