package com.example.FrameWorkCollection.http;

public interface ICallback {
//	Á½¿é¿ÕÐÄ×©
	void onSuccess(String result);

	void onFailure(Exception e);
}
