package com.example.FrameWorkCollection;

/**
 * javabean示例如何解析一个泛型
 * 
 * @author kangou
 * 
 */
public class User {
	// {"ret":200,"data":{"id":"2","account":"stay4it","email":"stay4it@163.com",
	// "username":"stay","password":"123456","avatar":"uploads\/avatar\/22d0b7428dea5e5e5e2dcfa17fec24f4.png",
	// "token":"4oqSQ35lrdXX5jPm0PZwS559xdvzavlIo6WPa8TBXBo="},"msg":""}
	public String id;
	public String account;
	public String email;
	public String token;

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", email=" + email
				+ ", token=" + token + "]";
	}

}
