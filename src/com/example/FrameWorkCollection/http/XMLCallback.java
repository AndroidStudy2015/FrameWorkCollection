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
 * ����ʵ����ICallback�е�parse����������������һ��������
 * ������������Ҫ��Ϊ���Ҹ������ĵط���parse�����Ĺ��̣�Ҫ��Ȼֱ���ڵ���ʱ�򣨼�MainActivity����д��̫ռ�ط���
 * 
 * @author kangou
 * 
 * @param <T>
 */
public abstract class XMLCallback<T> extends AbstractCallback<T> {

	@Override
	protected T bindData(String result) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
