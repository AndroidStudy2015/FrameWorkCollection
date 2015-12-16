package com.example.FrameWorkCollection.http;

import java.util.Map;

/**
 * 由于请求参数会越来越多，所以要建立一个Request类来封装请求参数
 * 
 * 
 */
public class Request {
	public String url;
	public String content;
	public Map<String, String> headers;

}
