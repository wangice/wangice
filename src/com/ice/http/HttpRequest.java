package com.ice.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {

	private String method = "GET";
	private int connectOutTime = 0;

	/**
	 * 设置请求的方式 post get
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		if (method.equalsIgnoreCase("get") || method.equalsIgnoreCase("post")) {
			this.method = method;
		}
	}

	/**
	 * 设置请求的超时时间
	 * 
	 * @param connectTime
	 */
	public void setConnectOutTime(int connectOutTime) {
		this.connectOutTime = connectOutTime;
	}

	/**
	 * 
	 * @param strUrl
	 * @return
	 * @throws IOException
	 */
	public InputStream httpGet(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		connection.setRequestMethod(method);
		connection.setConnectTimeout(connectOutTime);
		int code = connection.getResponseCode();
		if (code == 200) { // 请求成功
			InputStream inputStream = connection.getInputStream();

			return inputStream;
		} else {
			return null;
		}
	}

	/**
	 * 发送POST请求
	 * @param strUrl
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public InputStream httpPost(String strUrl, String param) throws IOException {
		InputStream inputStream = null;
		URL url = new URL(strUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		connection.setRequestMethod("POST");
		 // 发送POST请求必须设置如下两行
		connection.setDoOutput(true);
		connection.setDoInput(true);
		OutputStream outputStream = connection.getOutputStream();
		PrintWriter out = new PrintWriter(outputStream);
		out.print(param);
		out.flush();
		if (connection.getResponseCode() == 200) {
			inputStream =  connection.getInputStream();
		}
		out.close();
		return inputStream;
	}
}
