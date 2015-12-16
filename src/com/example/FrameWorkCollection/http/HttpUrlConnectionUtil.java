package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpStatus;

import android.location.Address;

/**
 * HttpClient已经过时了
 */
public class HttpUrlConnectionUtil {
	/**
	 * HttpURLConnection:GET请求的基本写法
	 * 
	 * @param url
	 * @param headers
	 *            需要增加的请求头headers，多个请求头存在map集合里
	 * @return
	 * @throws IOException
	 */
	public static String get(String url,Map<String, String>headers) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);
		
		addHeader(headers, connection)

		int status = connection.getResponseCode();
		if (status == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = connection.getInputStream();
			byte[] buffer = new byte[2048];
			int len;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			is.close();
			out.flush();
			out.close();
			return new String(out.toByteArray());
		}
		return null;
	}

	/**
	 * HttpURLConnection:POST请求的基本写法
	 * 
	 * @param url
	 * @param content
	 *            在这里传递参数，键值对形式
	 * @param headers
	 *            需要增加的请求头headers，多个请求头存在map集合里
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String content,
			Map<String, String> headers) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);
		connection.setDoOutput(true);

		// 记得加一个请求头header
		// connection.addRequestProperty("content-type", "application/json");
		// ★★★★★请求头有很多，这里在方法里传入一个map参数，键值对增加请求头
		addHeader(headers, connection);
		// 以下这两行为POST和GET的不同之处：
		// ★把content数据传到服务器的步骤（请求体传参）
		OutputStream os = connection.getOutputStream();
		os.write(content.getBytes());

		int status = connection.getResponseCode();
		if (status == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = connection.getInputStream();
			byte[] buffer = new byte[2048];
			int len;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			is.close();
			out.flush();
			out.close();
			return new String(out.toByteArray());
		}

		return content;

	}

	/**
	 * ★★★★★增加请求头
	 * 
	 * @param headers
	 * @param connection
	 */

	private static void addHeader(Map<String, String> headers,
			HttpURLConnection connection) {
		if (headers==null||headers.size()=0) {
			return;
		}
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			connection.addRequestProperty(entry.getKey(), entry.getValue());
		}
	}
}
