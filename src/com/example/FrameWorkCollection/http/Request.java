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

//	★★★★★★★★★★★★★回调接口五步走★★★★★★★★★★★★★
//	1.先建立这个接口interface，里面一般都会放几个抽象方法，供不同情况下当一块空心砖使用
//	2.将此接口设为成员变量
//	3.设置set方法
//	4.在必须写完的流程的不确定节点上，静静地放上“接口.抽象方法”充当空心砖
//	5.当调用者要走这个流程了，他必须把这块空心砖给实现了（即抽象方法给实现了）
	public ICallback iCallback;

	public void setCallback(ICallback iCallback) {
		this.iCallback = iCallback;
	}


}
