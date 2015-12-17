package com.example.FrameWorkCollection.http;

import java.io.IOException;
import java.net.HttpURLConnection;

import android.os.AsyncTask;

/**
 * 在onPreExecute执行请求网络之前的准备工作<br>
 * 在doInBackground执行网络请求的异步任务，得到connection，解析之，并返回请求结果<br>
 * 在onPostExecute中处理请求结果（接口回调）<br>
 * 
 * @author kangou
 * 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {
	private Request request;

	public RequestTask(Request request) {
		super();
		this.request = request;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Object doInBackground(Void... params) {
		try {
			// 访问网络要在子线程中运行
			// 成功，返回请求到的结果，原来是String，
			// 现在变了：这里用首先得到connection（因为connection是共同的，之后怎么解析connection就有变化了），在通过parse解析得到result
			HttpURLConnection connection = HttpUrlConnectionUtil
					.execute(request);

			return request.iCallback.parse(connection);
		} catch (Exception e) {
			// 失败,返回异常
			return e;
		}
	}

	@Override
	protected void onPostExecute(Object o) {// ★这里的o就是doInBackground的返回值
		super.onPostExecute(o);
		// ★★★此时，要对请求得到的结果做处理了，但此刻还不知道该怎么处理此结果，那就使用“接口.抽象方法”占位置
		if (o instanceof Exception) {
			// ★★★★★★★★★通过一个接口的抽象方法，把得到的结果传递到外面，让调用者决定这里该干什么
			// 换句话说：当一个流程必须走完，但还有一些节点没有确定时，在这个节点上放上一个接口的抽象方法，相当于盖房子的时候放一块空心砖，让使用这个房子的人去决定这块砖里放什么。
			// 在这里还有把这个接口对象归于谁的变量的问题，在这里这个接口是request请求参数的一个成员变量，并在其内部设置了set方法
			request.iCallback.onFailure((Exception) o);
		} else {
			request.iCallback.onSuccess(o);
		}
	}
}
