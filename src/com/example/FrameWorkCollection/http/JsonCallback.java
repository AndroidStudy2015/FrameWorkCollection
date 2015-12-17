package com.example.FrameWorkCollection.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.json.JSONException;
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
public abstract class JsonCallback<T> extends AbstractCallback<T> {

	@Override
	protected T bindData(String result) throws Exception {
		// ������������·�ʽ����������Ҫjson����User������м�ֵ��һһ��Ӧ������ѡȡjson��һ������ΪUser��
		// �ﵫ�������ԭ����ֱ��һ�������򣬱���һһ��Ӧ��ȱһ����

		// �����ϱߵ�result,��ʱ������һ��json
		JSONObject json = new JSONObject(result);
		// ��ȡ��Ϊ��data���Ķ�Ӧ��ֵ���������ֱֵ�ӱ��һ��JSONObject
		JSONObject data = json.optJSONObject("data");
		Gson gson = new Gson();
		// �õ�����Callback<T>����T�ľ������ͣ������֮���ǻ�ó���ķ��Ͳ�����ʵ������
		Type type = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		// ���ؽ���data��õ��Ķ���clz
		return gson.fromJson(data.toString(), type);
	}
}
