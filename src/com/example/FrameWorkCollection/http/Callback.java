package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
public abstract class Callback<T> implements ICallback<T> {

	private Class<T> clz;

	// 前两个方法不需要在这里实现，所以此处不实现

	// @Override
	// public void onSuccess(T result) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFailure(Exception e) {
	// // TODO Auto-generated method stub
	//
	// }
	/**
	 * 解析网络请求回来的connection，将connection变成json，再变成对应的javabean
	 */
	@Override
	public T parse(HttpURLConnection connection) throws Exception {

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
			String result = new String(out.toByteArray());

			// ★如果按照以下方式解析，不需要json串和User类的所有键值对一一对应，可以选取json的一部分作为User类
			// ★但是如果用原来的直接一键解析则，必须一一对应，缺一不可

			// 解析上边的result,此时，他是一个json
			JSONObject json = new JSONObject(result);
			// 获取名为“data”的对应的值，并把这个值直接变成一个JSONObject
			JSONObject data = json.optJSONObject("data");
			Gson gson = new Gson();
			// 返回解析data后得到的对象clz
			return gson.fromJson(data.toString(), clz);
		}
		return null;
	}

	/**
	 * 告诉gson要解析成那个javabean
	 * 
	 * @param clz
	 *            要解析成的类，如：User.class
	 * @return
	 */
	public ICallback setReturnType(Class<T> clz) {
		this.clz = clz;
		return this;

	}

}
