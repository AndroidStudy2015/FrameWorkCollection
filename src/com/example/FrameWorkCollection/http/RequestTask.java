package com.example.FrameWorkCollection.http;

import java.io.IOException;
import java.net.HttpURLConnection;

import android.os.AsyncTask;

/**
 * ��onPreExecuteִ����������֮ǰ��׼������<br>
 * ��doInBackgroundִ������������첽���񣬵õ�connection������֮��������������<br>
 * ��onPostExecute�д������������ӿڻص���<br>
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
			// ��������Ҫ�����߳�������
			// �ɹ����������󵽵Ľ����ԭ����String��
			// ���ڱ��ˣ����������ȵõ�connection����Ϊconnection�ǹ�ͬ�ģ�֮����ô����connection���б仯�ˣ�����ͨ��parse�����õ�result
			HttpURLConnection connection = HttpUrlConnectionUtil
					.execute(request);

			return request.iCallback.parse(connection);
		} catch (Exception e) {
			// ʧ��,�����쳣
			return e;
		}
	}

	@Override
	protected void onPostExecute(Object o) {// �������o����doInBackground�ķ���ֵ
		super.onPostExecute(o);
		// �����ʱ��Ҫ������õ��Ľ���������ˣ����˿̻���֪������ô����˽�����Ǿ�ʹ�á��ӿ�.���󷽷���ռλ��
		if (o instanceof Exception) {
			// ����������ͨ��һ���ӿڵĳ��󷽷����ѵõ��Ľ�����ݵ����棬�õ����߾�������ø�ʲô
			// ���仰˵����һ�����̱������꣬������һЩ�ڵ�û��ȷ��ʱ��������ڵ��Ϸ���һ���ӿڵĳ��󷽷����൱�ڸǷ��ӵ�ʱ���һ�����ש����ʹ��������ӵ���ȥ�������ש���ʲô��
			// �����ﻹ�а�����ӿڶ������˭�ı��������⣬����������ӿ���request���������һ����Ա�������������ڲ�������set����
			request.iCallback.onFailure((Exception) o);
		} else {
			request.iCallback.onSuccess(o);
		}
	}
}
