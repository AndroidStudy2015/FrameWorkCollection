package com.example.FrameWorkCollection.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * ����ʵ����ICallback�е�parse����������������һ��������
 * ������������Ҫ��Ϊ���Ҹ������ĵط���parse�����Ĺ��̣�Ҫ��Ȼֱ���ڵ���ʱ�򣨼�MainActivity����д��̫ռ�ط���
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class Callback<T> implements ICallback<T> {

	private Class<T> clz;

	// ǰ������������Ҫ������ʵ�֣����Դ˴���ʵ��

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
	 * �����������������connection����connection���json���ٱ�ɶ�Ӧ��javabean
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

			// ������������·�ʽ����������Ҫjson����User������м�ֵ��һһ��Ӧ������ѡȡjson��һ������ΪUser��
			// �ﵫ�������ԭ����ֱ��һ�������򣬱���һһ��Ӧ��ȱһ����

			// �����ϱߵ�result,��ʱ������һ��json
			JSONObject json = new JSONObject(result);
			// ��ȡ��Ϊ��data���Ķ�Ӧ��ֵ���������ֱֵ�ӱ��һ��JSONObject
			JSONObject data = json.optJSONObject("data");
			Gson gson = new Gson();
			// ���ؽ���data��õ��Ķ���clz
			return gson.fromJson(data.toString(), clz);
		}
		return null;
	}

	/**
	 * ����gsonҪ�������Ǹ�javabean
	 * 
	 * @param clz
	 *            Ҫ�����ɵ��࣬�磺User.class
	 * @return
	 */
	public ICallback setReturnType(Class<T> clz) {
		this.clz = clz;
		return this;

	}

}
