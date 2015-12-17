package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 仅仅实现了ICallback中的parse方法，所以他还是一个抽象类
 * 这个类的作用主要是为了找个单独的地方放parse解析的过程，要不然直接在调用时候（即MainActivity）里写，太占地方了
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class XMLCallback<T> extends AbstractCallback<T> {

	@Override
	protected T bindData(String result) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
