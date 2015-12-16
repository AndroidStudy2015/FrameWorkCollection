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
 * HttpClient�Ѿ���ʱ��
 */
public class HttpUrlConnectionUtil {

	/**
	 * ��GET��POST������һ��������©���ⲿ����ԭ�е�GET��POST���󷽷���Ϊprivate�������������˱��˵�ѧϰ�ɱ�
	 * ����methodȷ�������ĸ�����
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
	 * HttpURLConnection:GET����Ļ���д��
	 * 
	 * @param request
	 *            ���װ�˸�����������Ķ���
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
	 * HttpURLConnection:POST����ķ�װ1
	 * 
	 * @param request
	 *            ���װ�˸�����������Ķ���
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

		// �ǵü�һ������ͷheader
		// connection.addRequestProperty("content-type", "application/json");
		// ����������ͷ�кܶ࣬�����ڷ����ﴫ��һ��map��������ֵ����������ͷ
		addHeader(request.headers, connection);
		// ����������ΪPOST��GET�Ĳ�֮ͬ����
		// ���content���ݴ����������Ĳ��裨�����崫�Σ�
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
	 * ��������������ͷ
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
