package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpStatus;

/**
 * HttpClient已经过时了
 */
public class HttpUrlConnectionUtil {

	/**
	 * 把GET、POST请求变成一个方法暴漏给外部（将原有的GET、POST请求方法变为private），这样减少了别人的学习成本
	 * 根据method确定调用哪个请求
	 * 
	 * @throws IOException
	 */

	public static String execute(Request request) throws IOException {
		switch (request.method) {
		case GET:
		case DELETE:
			return get(request);
		case POST:
		case PUT:
			return post(request);

		}

		return null;

	}

	/**
	 * HttpURLConnection:GET请求的基本写法
	 * 
	 * @param request
	 *            ★封装了各种请求参数的对象
	 * @return
	 * @throws IOException
	 */
	public static String get(Request request) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(request.url)
				.openConnection();
		connection.setRequestMethod(request.method.name());
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);

		addHeader(request.headers, connection);

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
	 * HttpURLConnection:POST请求的封装1
	 * 
	 * @param request
	 *            ★封装了各种请求参数的对象
	 * @return
	 * @throws IOException
	 */
	public static String post(Request request) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(request.url)
				.openConnection();
		connection.setRequestMethod(request.method.name());
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);
		connection.setDoOutput(true);

		// 记得加一个请求头header
		// connection.addRequestProperty("content-type", "application/json");
		// ★★★★★请求头有很多，这里在方法里传入一个map参数，键值对增加请求头
		addHeader(request.headers, connection);
		// 以下这两行为POST和GET的不同之处：
		// ★把content数据传到服务器的步骤（请求体传参）
		OutputStream os = connection.getOutputStream();
		os.write(request.content.getBytes());

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
	 * ★★★★★增加请求头
	 * 
	 * @param headers
	 * @param connection
	 */

	private static void addHeader(Map<String, String> headers,
			HttpURLConnection connection) {
		if (headers == null || headers.size() == 0) {
			return;
		}
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			connection.addRequestProperty(entry.getKey(), entry.getValue());
		}
	}
}
