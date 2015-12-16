package com.example.FrameWorkCollection.http;

import java.util.Map;

/**
 * �������������Խ��Խ�࣬����Ҫ����һ��Request������װ�������
 * 
 * 
 */
public class Request {
	public enum RequestMethod {
		GET, POST, PUT, DELETE
	}

	public String url;
	/**
	 * POST���������ﴫ�ݲ�������ֵ����ʽ
	 */
	public String content;
	/**
	 * ��Ҫ���ӵ�����ͷheaders���������ͷ����map������
	 */
	public Map<String, String> headers;
	/**
	 * ���󷽷�
	 */
	public RequestMethod method;

	/**
	 * ֻ�Ǵ���url��Ĭ��ΪGET����
	 * 
	 * @param url
	 */
	public Request(String url) {
		this.url = url;
		this.method = RequestMethod.GET;
	}

	public Request(String url, RequestMethod method) {
		this.url = url;
		this.method = method;
	}

}
