package com.example.FrameWorkCollection;

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
		testHttpPostOnStubThread();
	}

	private void testHttpPostOnStubThread() {
		String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
		String content = "account=stay4it&password=123456";
		Request request = new Request(url, Request.RequestMethod.POST);
		// 此刻，我们想使用AsyncTask子线程里请求网络得到的数据result，如何将得到这个result呢，要用★★★★★回调★★★★★了，难点
		request.setCallback(new ICallback() {

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
}
