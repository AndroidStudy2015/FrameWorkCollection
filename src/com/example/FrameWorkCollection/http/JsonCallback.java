package com.example.FrameWorkCollection.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 仅仅实现了ICallback中的parse方法，所以他还是一个抽象类
 * 这个类的作用主要是为了找个单独的地方放parse解析的过程，要不然直接在调用时候（即MainActivity）里写，太占地方了
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {

	@Override
	protected T bindData(String result) throws Exception {
		// ★如果按照以下方式解析，不需要json串和User类的所有键值对一一对应，可以选取json的一部分作为User类
		// ★但是如果用原来的直接一键解析则，必须一一对应，缺一不可

		// 解析上边的result,此时，他是一个json
		JSONObject json = new JSONObject(result);
		// 获取名为“data”的对应的值，并把这个值直接变成一个JSONObject
		JSONObject data = json.optJSONObject("data");
		Gson gson = new Gson();
		// 得到类名Callback<T>泛型T的具体类型，简而言之就是获得超类的泛型参数的实际类型
		Type type = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		// 返回解析data后得到的对象clz
		return gson.fromJson(data.toString(), type);
	}
}
