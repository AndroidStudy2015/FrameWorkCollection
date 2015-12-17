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
	 * �����߳���ִ���������󣬲�����String��Ŀǰ����Ϊ��testHttpPostOnStubThreadForGeneric�޸��˴��룬
	 * ʹ���������������������
	 */
	private void testHttpPostOnStubThread() {
		String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
		String content = "account=stay4it&password=123456";
		Request request = new Request(url, Request.RequestMethod.POST);
		// �˿̣�������ʹ��AsyncTask���߳�����������õ�������result����ν��õ����result�أ�Ҫ�á�����ص��������ˣ��ѵ�
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
	 * �����߳���ִ���������󣬲����ط���T������һ��javabean
	 */
	private void testHttpPostOnStubThreadForGeneric() {// ����
		String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
		String content = "account=stay4it&password=123456";
		Request request = new Request(url, Request.RequestMethod.POST);
		// �˿̣�������ʹ��AsyncTask���߳�����������õ�������result����ν��õ����result�أ�Ҫ�á�����ص��������ˣ��ѵ�
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
			// ���������Ƶú����setReturnType���ص���this����Callback�Ķ������û����Է������ﵱsetCallback�Ĳ���ʹ��
		}.setReturnType(User.class));
		request.content = content;
		RequestTask task = new RequestTask(request);
		task.execute();

	}
}
