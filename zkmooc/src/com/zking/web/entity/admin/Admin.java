package com.zking.web.entity.admin;

/**
 * 管理员
 * 
 * @author 胡博
 * 
 */
public class Admin {
	private String acount; // 管理员账号
	private String apass; // 登陆密码

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getApass() {
		return apass;
	}

	public void setApass(String apass) {
		this.apass = apass;
	}

}
