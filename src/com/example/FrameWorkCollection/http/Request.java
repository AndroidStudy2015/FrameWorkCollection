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

//	��������������ص��ӿ��岽�ߡ�������������
//	1.�Ƚ�������ӿ�interface������һ�㶼��ż������󷽷�������ͬ����µ�һ�����שʹ��
//	2.���˽ӿ���Ϊ��Ա����
//	3.����set����
//	4.�ڱ���д������̵Ĳ�ȷ���ڵ��ϣ������ط��ϡ��ӿ�.���󷽷����䵱����ש
//	5.��������Ҫ����������ˣ��������������ש��ʵ���ˣ������󷽷���ʵ���ˣ�
	public ICallback iCallback;

	public void setCallback(ICallback iCallback) {
		this.iCallback = iCallback;
	}


}
