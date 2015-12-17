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
 * ���࣬�����еĽ�����Callback�̳�����ֻ��ҪдbindData����ʵ�ֲ�ͬ�Ľ�������
 * ����ʵ����ICallback�е�parse����������������һ��������
 * ������������Ҫ��Ϊ���Ҹ������ĵط���parse�����Ĺ��̣�Ҫ��Ȼֱ���ڵ���ʱ�򣨼�MainActivity����д��̫ռ�ط���
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class AbstractCallback<T> implements ICallback<T> {

	// private Class<T> clz;

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
			// ��������ϵ����д����ǹ��õģ����Գ�ȡ������������ķ�����Ҫ���ݲ�ͬ���ȥ����
			return bindData(result);
		}
		return null;
	}

	/**
	 * ��������ȥʵ����ν������result����Ϊ����result��xml��Ҳ������json������bitmap��
	 * 
	 * @param result
	 * @return
	 */
	protected abstract T bindData(String result) throws Exception;

	// ���� Type type = ((ParameterizedType) getClass().getGenericSuperclass())
	// .getActualTypeArguments()[0];�Ͳ�����Ҫ����ķ�����
	// /**
	// * ����gsonҪ�������Ǹ�javabean
	// *
	// * @param clz
	// * Ҫ�����ɵ��࣬�磺User.class
	// * @return
	// */
	// public ICallback setReturnType(Class<T> clz) {
	// this.clz = clz;
	// return this;
	//
	// }

}
