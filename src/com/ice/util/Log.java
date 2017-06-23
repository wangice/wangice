package com.ice.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.apache.log4j.PropertyConfigurator;

import com.ice.time.Dateu;

/**
 * 
 * 日志处理.
 * 
 * @Author: xuzewen@eybond.com
 * @create on 2008-10-01
 * 
 */
public class Log
{
	/** 使能标准输出. */
	public static final byte OUTPUT_STDOUT = 1 << 0;
	/** 使能文件输出. */
	public static final byte OUTPUT_FILE = 1 << 1;
	//
	private static final byte RECORD = 0x00, TRACE = 0x01, DEBUG = 0x02, INFO = 0x03, WARN = 0x04, ERROR = 0x05, FAULT = 0x06, OPER = 0x07;
	private static byte level = DEBUG;
	private static boolean single = false;
	private static AtomicBoolean thread = new AtomicBoolean(false);
	private static byte output = Log.OUTPUT_STDOUT;
	private static String path = "./";
	private static int pid = 0;
	private static Consumer<String> cb = null;
	private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
	public static final ConcurrentHashMap<String, String> ignores = new ConcurrentHashMap<String, String>();
	private static Properties pro4log4j = new Properties();

	static
	{
		try
		{
			Log.setLog4j();
			Log.pid = Misc.forceInt0(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static final void init(String path, int o)
	{
		Log.path = path == null ? Log.path : path;
		Log.output = (byte) o;
		//
		if (Log.isOutPutFile() && !Log.thread.getAndSet(true))
			new Thread(() -> Log.svc()).start();
	}

	public static final void setLevel(String lev)
	{
		if ("RECORD".equals(lev))
			Log.setRecord();
		if ("TRACE".equals(lev))
			Log.setTrace();
		if ("DEBUG".equals(lev))
			Log.setDebug();
		if ("INFO".equals(lev))
			Log.setInfo();
		if ("WARN".equals(lev))
			Log.setWarn();
		if ("ERROR".equals(lev))
			Log.setError();
		if ("FAULT".equals(lev))
			Log.setFault();
		if ("OPER".equals(lev))
			Log.setOper();
	}

	public static final String getLevel()
	{
		switch (Log.level)
		{
		case Log.RECORD:
			return "RECORD";
		case Log.TRACE:
			return "TRACE";
		case Log.DEBUG:
			return "DEBUG";
		case Log.INFO:
			return "INFO";
		case Log.WARN:
			return "WARN";
		case Log.ERROR:
			return "ERROR";
		case Log.FAULT:
			return "FAULT";
		case Log.OPER:
			return "OPER";
		default:
			return "NONE";
		}
	}

	public static final void single(boolean s)
	{
		Log.single = s;
	}

	public static final void setRecord()
	{
		Log.level = Log.RECORD;
		Log.setLog4j();
	}

	public static final void setTrace()
	{
		Log.level = Log.TRACE;
		Log.setLog4j();
	}

	public static final void setDebug()
	{
		Log.level = Log.DEBUG;
		Log.setLog4j();
	}

	public static final void setInfo()
	{
		Log.level = Log.INFO;
		Log.setLog4j();
	}

	public static final void setWarn()
	{
		Log.level = Log.WARN;
		Log.setLog4j();
	}

	public static final void setError()
	{
		Log.level = Log.ERROR;
		Log.setLog4j();
	}

	public static final void setFault()
	{
		Log.level = Log.FAULT;
		Log.setLog4j();
	}

	public static final void setOper()
	{
		Log.level = Log.OPER;
		Log.setLog4j();
	}

	public static final boolean isRecord()
	{
		return Log.single ? (Log.level == Log.RECORD) : Log.level <= Log.RECORD;
	}

	public static final boolean isTrace()
	{
		return Log.single ? (Log.level == Log.TRACE) : Log.level <= Log.TRACE;
	}

	public static final boolean isDebug()
	{
		return Log.single ? (Log.level == Log.DEBUG) : Log.level <= Log.DEBUG;
	}

	public static final boolean isInfo()
	{
		return Log.single ? (Log.level == Log.INFO) : Log.level <= Log.INFO;
	}

	public static final boolean isWarn()
	{
		return Log.single ? (Log.level == Log.WARN) : Log.level <= Log.WARN;
	}

	public static final boolean isError()
	{
		return Log.single ? (Log.level == Log.ERROR) : Log.level <= Log.ERROR;
	}

	public static final boolean isFault()
	{
		return Log.single ? (Log.level == Log.FAULT) : Log.level <= Log.FAULT;
	}

	public static final boolean isOper()
	{
		return Log.single ? (Log.level == Log.OPER) : Log.level <= Log.OPER;
	}

	public static final void record(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.RECORD)
				Log.log("RECO", format, args);
			return;
		}
		if (Log.level <= Log.RECORD)
			Log.log("RECO", format, args);
	}

	public static final void trace(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.TRACE)
				Log.log("TRAC", format, args);
			return;
		}
		if (Log.level <= Log.TRACE)
			Log.log("TRAC", format, args);
	}

	public static final void debug(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.DEBUG)
				Log.log("DEBU", format, args);
			return;
		}
		if (Log.level <= Log.DEBUG)
			Log.log("DEBU", format, args);
	}

	public static final void info(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.INFO)
				Log.log("INFO", format, args);
			return;
		}
		if (Log.level <= Log.INFO)
			Log.log("INFO", format, args);
	}

	public static final void warn(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.WARN)
				Log.log("WARN", format, args);
			return;
		}
		if (Log.level <= Log.WARN)
			Log.log("WARN", format, args);
	}

	public static final void error(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.ERROR)
				Log.log("ERRO", format, args);
			return;
		}
		if (Log.level <= Log.ERROR)
			Log.log("ERRO", format, args);
	}

	public static final void fault(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.FAULT)
				Log.log("FAUL", format, args);
			return;
		}
		if (Log.level <= Log.FAULT)
			Log.log("FAUL", format, args);
	}

	public static final void oper(String format, Object... args)
	{
		if (Log.single)
		{
			if (Log.level == Log.OPER)
				Log.log("OPER", format, args);
			return;
		}
		Log.log("OPER", format, args);
	}

	public static final void setOutput(int output)
	{
		Log.output = (byte) output;
		if ((Log.output & Log.OUTPUT_FILE) != 0 && !Log.thread.getAndSet(true))
			new Thread(() -> Log.svc()).start();
	}

	public static final void outputNet(Consumer<String> cb)
	{
		Log.cb = cb;
	}

	public static final void ignoreClass(String cls)
	{
		Log.ignores.put(cls, "");
	}

	/** only for log4j. */
	public static final void log4log4j(String str)
	{
		if (Log.isOutPutFile())
			Log.queue.add(str); /* push to queue. */
		//
		if (Log.isOutPutStdout())
			System.out.println(str);
	}

	/** only for log4j. */
	private static final void setLog4j()
	{
		String tmp = null;
		if (Log.level == Log.RECORD)
			tmp = "ALL";
		else if (Log.level == Log.TRACE)
			tmp = "TRACE";
		else if (Log.level == Log.DEBUG)
			tmp = "INFO";
		else if (Log.level == Log.INFO)
			tmp = "INFO";
		else if (Log.level == Log.WARN)
			tmp = "WARN";
		else
			tmp = "ERROR";
		Log.pro4log4j.setProperty("log4j.rootLogger", Misc.printf2Str("%s,D", tmp));
		Log.pro4log4j.setProperty("log4j.appender.D", "org.apache.log4j.ConsoleAppender");
		Log.pro4log4j.setProperty("log4j.appender.D.layout", "misc.Log4jAdapter  ");
		PropertyConfigurator.configure(Log.pro4log4j);
	}

	private static final void log(String level, String format, Object... args)
	{
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		String cls = stacks[2].getClassName();
		Enumeration<String> keys = Log.ignores.keys();
		while (keys.hasMoreElements())
		{
			String key = keys.nextElement();
			if (cls.startsWith(key))
				return;
		}
		//
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Date now = new Date();
		StringBuilder prefix = new StringBuilder();
		prefix.append(Dateu.parseDateyyyyMMddHHmmssms(now)).append("[").append(level).append("]").append(Thread.currentThread().getId()).append("(").append(cls).append(" ").append(stacks[2].getMethodName()).append(" ").append(stacks[2].getLineNumber()).append(") ");
		PrintStream ps = new PrintStream(bos);
		ps.printf("%s", prefix);
		ps.printf(format, args);
		ps.printf("\n");
		//
		String str = bos.toString();
		//
		if (Log.isOutPutFile() || Log.cb != null)
			Log.queue.add(str); /* push to queue. */
		//
		if (Log.isOutPutStdout())
			System.out.print(str);
	}

	private static final void svc()
	{
		while (true)
		{
			String str = Log.queue.poll();
			if (str == null)
			{
				Misc.sleep(500);
				continue;
			}
			if (Log.isOutPutFile())
				Log.write2File(str);
			if (Log.cb != null)
				Misc.exeConsumer(Log.cb, str);
		}
	}

	private static final void write2File(String str)
	{
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(new File(Log.path + Dateu.parseDateyyyy_MM_dd(new Date()) + ".log"), "rw");
			raf.seek(raf.length());
			raf.write(str.getBytes());
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			Misc.closeRaf(raf);
		}
	}

	public static final String trace(Throwable e)
	{
		StringBuilder strb = new StringBuilder(0x200);
		StackTraceElement ste[] = e.getStackTrace();
		strb.append(e).append(Misc.LINE);
		for (int i = 0; i < ste.length; i++)
			strb.append(ste[i].toString()).append(Misc.LINE);
		return strb.toString();
	}

	public static final int getPid()
	{
		return Log.pid;
	}

	private static final boolean isOutPutStdout()
	{
		return (Log.output & Log.OUTPUT_STDOUT) != 0;
	}

	private static final boolean isOutPutFile()
	{
		return (Log.output & Log.OUTPUT_FILE) != 0;
	}

	public static final void logBean(Object bean, String className, StringBuilder strb)
	{
		Field field[] = bean.getClass().getDeclaredFields();
		Arrays.sort(field, new Comparator<Field>()
		{
			public int compare(Field o1, Field o2)
			{
				return o1.getName().compareTo(o2.getName());
			}
		});
		try
		{
			for (int i = 0; i < field.length; i++)
			{
				if (field[i].getType().getName().equals(bean.getClass().getName())) /* 禁止递归自己. */
					continue;
				field[i].setAccessible(true);
				if (!field[i].isAccessible())
					continue;
				String fieldName = field[i].getName();
				Object o = field[i].get(bean);
				Class<?> t = field[i].getType();
				if (o != null && t.isArray())
				{
					int arr_length = Array.getLength(o);
					for (int k = 0; k < arr_length; k++)
					{
						Object obj = Array.get(o, k);
						if (obj != null && obj.getClass().isInstance(obj) && !Log.isSimpleObject(obj))
						{
							String index = o.getClass().getSimpleName();
							index = index.substring(0, index.length() - 1) + k + "]";
							Log.logBean(obj, className + "." + index + "." + obj.getClass().getSimpleName(), strb);
						} else
							strb.append(className + "." + fieldName + "[" + k + "] = " + obj).append(Misc.LINE);
					}
				} else if (o != null && o instanceof Date)
					strb.append(className + "." + fieldName + " = " + Dateu.parseDateyyyyMMddHHmmss((Date) o)).append(Misc.LINE);
				else if (o != null && t.isInstance(o) && !Log.isSimpleObject(o))
					Log.logBean(o, className + "." + o.getClass().getSimpleName(), strb);
				else
					strb.append(className + "." + fieldName + " = " + o).append(Misc.LINE);
			}
		} catch (Exception e)
		{
			if (Log.isError())
				Log.error("%s", Log.trace(e));
		}
	}

	private static final boolean isSimpleObject(Object o)
	{
		return o instanceof String || o instanceof Number || o instanceof Character || o instanceof Boolean;
	}
}
