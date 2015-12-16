package com.example.FrameWorkCollection.http;

import java.util.Map;

/**
 * 由于请求参数会越来越多，所以要建立一个Request类来封装请求参数
 * 
 * 
 */
public class Request {
	public enum RequestMethod {
		GET, POST, PUT, DELETE
	}

	public String url;
	/**
	 * POST请求在这里传递参数，键值对形式
	 */
	public String content;
	/**
	 * 需要增加的请求头headers，多个请求头存在map集合里
	 */
	public Map<String, String> headers;
	/**
	 * 请求方法
	 */
	public RequestMethod method;

	/**
	 * 只是传递url，默认为GET方法
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
