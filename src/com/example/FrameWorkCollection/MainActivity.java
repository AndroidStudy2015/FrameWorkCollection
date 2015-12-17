package com.example.FrameWorkCollection;

import com.example.FrameWorkCollection.http.Callback;
import com.example.FrameWorkCollection.http.ICallback;
import com.example.FrameWorkCollection.http.Request;
import com.example.FrameWorkCollection.http.RequestTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// testHttpPostOnStubThread();
		testHttpPostOnStubThreadForGeneric();
	}

	/**
	 * 在子线程中执行网络请求，并返回String，目前由于为了testHttpPostOnStubThreadForGeneric修改了代码，
	 * 使得下面这个方法不可用了
	 */
	private void testHttpPostOnStubThread() {
		String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
		String content = "account=stay4it&password=123456";
		Request request = new Request(url, Request.RequestMethod.POST);
		// 此刻，我们想使用AsyncTask子线程里请求网络得到的数据result，如何将得到这个result呢，要用★★★★★回调★★★★★了，难点
		request.setCallback(new Callback<String>() {

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Log.e("stay", "testHttpPost return" + result);
			}

			@Override
			public void onFailure(Exception e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
		});
		request.content = content;
		RequestTask task = new RequestTask(request);
		task.execute();

	}

	/**
	 * 在子线程中执行网络请求，并返回泛型T，任意一个javabean
	 */
	private void testHttpPostOnStubThreadForGeneric() {// 泛型
		String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
		String content = "account=stay4it&password=123456";
		Request request = new Request(url, Request.RequestMethod.POST);
		// 此刻，我们想使用AsyncTask子线程里请求网络得到的数据result，如何将得到这个result呢，要用★★★★★回调★★★★★了，难点
		request.setCallback(new Callback<User>() {

			@Override
			public void onSuccess(User result) {
				// TODO Auto-generated method stub
				Log.e("stay", "testHttpPost return" + result.toString());
			}

			@Override
			public void onFailure(Exception e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
			// 下面这句设计得很巧妙，setReturnType返回的是this，即Callback的对象，正好还可以放在这里当setCallback的参数使用
		}.setReturnType(User.class));
		request.content = content;
		RequestTask task = new RequestTask(request);
		task.execute();

	}
}
