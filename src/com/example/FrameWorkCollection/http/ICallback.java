package com.example.FrameWorkCollection.http;

import java.io.IOException;
import java.net.HttpURLConnection;

//因为在onSuccess中接受的result可能是String、Drawable、等其他类型不确定，所以使用★泛型
public interface ICallback<T> {
	// 两块空心砖
	/**
	 * 请求成功时的回调
	 * 
	 * @param result
	 */
	void onSuccess(T result);

	/**
	 * 请求失败时的回调
	 * 
	 * @param e
	 */
	void onFailure(Exception e);

	/**
	 * 解析网络请求下来的connection（不同的需求要不同的解析器来解析） <br>
	 * 如：文件要下载到一个路径，json要解析成相应的对象， bitmap要解析成图片
	 * 
	 * @param connection
	 *            网络请求下来的connection
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	T parse(HttpURLConnection connection) throws Exception;
}
