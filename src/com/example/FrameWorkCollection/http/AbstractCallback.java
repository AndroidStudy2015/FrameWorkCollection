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
 * 基类，让所有的解析的Callback继承他，只需要写bindData即可实现不同的解析需求
 * 仅仅实现了ICallback中的parse方法，所以他还是一个抽象类
 * 这个类的作用主要是为了找个单独的地方放parse解析的过程，要不然直接在调用时候（即MainActivity）里写，太占地方了
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class AbstractCallback<T> implements ICallback<T> {

	// private Class<T> clz;

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
			// ★此行以上的所有代码是公用的，所以抽取出来，而下面的方法需要根据不同情况去解析
			return bindData(result);
		}
		return null;
	}

	/**
	 * ★让子类去实现如何解析这个result（因为肯能result是xml，也可能是json，或者bitmap）
	 * 
	 * @param result
	 * @return
	 */
	protected abstract T bindData(String result) throws Exception;

	// 有了 Type type = ((ParameterizedType) getClass().getGenericSuperclass())
	// .getActualTypeArguments()[0];就不在需要下面的方法了
	// /**
	// * 告诉gson要解析成那个javabean
	// *
	// * @param clz
	// * 要解析成的类，如：User.class
	// * @return
	// */
	// public ICallback setReturnType(Class<T> clz) {
	// this.clz = clz;
	// return this;
	//
	// }

}
