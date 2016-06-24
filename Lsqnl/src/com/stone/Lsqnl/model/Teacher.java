package com.stone.Lsqnl.model;

import cn.bmob.v3.BmobUser;

/**
 * 用户实体类
 * @date 2014-4-24
 * @author Stone
 */
@SuppressWarnings("serial")
public class Teacher extends User {

	// 父类中已经存在的属性
	// private String id;
	// private String username;
	// private String password;
	// private String email;
	// private String regTime;

	private String rname;
	private String message;
	private String dnum;
	//private BmobFile picUser; 	// 头像

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public void setDnum(String dnum) {
		this.dnum = dnum;
	}
	public String getDnum() {
		return dnum;
	}

	
	
}
