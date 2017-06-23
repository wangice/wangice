package com.ice.util;

import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.ice.time.Dateu;

/**
 *
 * @Created on: 2015年9月7日 下午7:25:16
 * @Author: xuzewen@eybond.com
 * 
 */
public class Log4jAdapter extends Layout
{
	public void activateOptions()
	{

	}

	public String format(LoggingEvent event)
	{
		String cls = event.getLocationInformation().getClassName();
		Enumeration<String> keys = Log.ignores.keys();
		while (keys.hasMoreElements())
		{
			String key = keys.nextElement();
			if (cls.startsWith(key))
				return null;
		}
		StringBuilder strb = new StringBuilder();
		strb.append(Dateu.parseDateyyyyMMddHHmmssms(new Date(event.getTimeStamp())));
		if (Level.ALL.toString().equals(event.getLevel().toString()))
			strb.append("[RECO]");
		if (Level.TRACE.toString().equals(event.getLevel().toString()))
			strb.append("[TRAC]");
		if (Level.DEBUG.toString().equals(event.getLevel().toString()))
			strb.append("[DEBU]");
		if (Level.INFO.toString().equals(event.getLevel().toString()))
			strb.append("[INFO]");
		if (Level.WARN.toString().equals(event.getLevel().toString()))
			strb.append("[WARN]");
		if (Level.ERROR.toString().equals(event.getLevel().toString()))
			strb.append("[ERRO]");
		strb.append(Misc.forceInt0(event.getThreadName().substring(event.getThreadName().lastIndexOf("-") + 1, event.getThreadName().length())));
		strb.append("(").append(cls).append(" ").//
				append(event.getLocationInformation().getMethodName()).append(" ").//
				append(event.getLocationInformation().getLineNumber()).//
				append(") ");
		strb.append(event.getMessage());
		Log.log4log4j(strb.toString());
		return null;
	}

	public boolean ignoresThrowable()
	{
		return false;
	}
}
