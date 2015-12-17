package com.example.FrameWorkCollection.http;

import java.io.IOException;
import java.net.HttpURLConnection;

//��Ϊ��onSuccess�н��ܵ�result������String��Drawable�����������Ͳ�ȷ��������ʹ�áﷺ��
public interface ICallback<T> {
	// �������ש
	/**
	 * ����ɹ�ʱ�Ļص�
	 * 
	 * @param result
	 */
	void onSuccess(T result);

	/**
	 * ����ʧ��ʱ�Ļص�
	 * 
	 * @param e
	 */
	void onFailure(Exception e);

	/**
	 * ������������������connection����ͬ������Ҫ��ͬ�Ľ������������� <br>
	 * �磺�ļ�Ҫ���ص�һ��·����jsonҪ��������Ӧ�Ķ��� bitmapҪ������ͼƬ
	 * 
	 * @param connection
	 *            ��������������connection
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	T parse(HttpURLConnection connection) throws Exception;
}
