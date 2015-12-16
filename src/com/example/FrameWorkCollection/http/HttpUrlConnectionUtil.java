package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpStatus;

import android.location.Address;

/**
 * HttpClient�Ѿ���ʱ��
 */
public class HttpUrlConnectionUtil {
	/**
	 * HttpURLConnection:GET����Ļ���д��
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);

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
	 * HttpURLConnection:POST����Ļ���д��
	 * 
	 * @param url
	 * @param content
	 *            �����ﴫ�ݲ�������ֵ����ʽ
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String content) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(15 * 3000);
		connection.setReadTimeout(15 * 3000);
		connection.setDoOutput(true);

		// ����������ΪPOST��GET�Ĳ�֮ͬ����
		// �ǵü�һ������ͷheader
		connection.addRequestProperty("content-type", "application/json");
		// ��content���ݴ����������Ĳ��裨�����崫�Σ�
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

}
