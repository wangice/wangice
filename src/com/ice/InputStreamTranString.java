package com.ice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTranString {
	
	/**
	 * 将InputStream流转入
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String transformString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		
		while ((length = inputStream.read(buf, 0, buf.length)) > 0) {
			baos.write(buf, 0, length);
		}
		baos.flush();
		return baos.toString();
	}
}
