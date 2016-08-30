package com.zking.web.entity.teacher;

/**
 * 教师
 * 
 * @author 胡博
 * 
 */
public class Teacher {
	private String tuid; // 教师uid
	private String tacount; // 教师账号
	private String tpass;
	private String tname; // 教师姓名
	private String tsex; // 教师性别
	private int tage; // 教师年龄
	private String timage; // 教师头像
	private String tinfo; // 教师简介
	private String tphone; // 教师手机号
	private String tmail; // 教师邮箱

	public Teacher() {

	}


	public String getTpass() {
		return tpass;
	}


	public void setTpass(String tpass) {
		this.tpass = tpass;
	}


	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public String getTacount() {
		return tacount;
	}

	public void setTacount(String tacount) {
		this.tacount = tacount;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public int getTage() {
		return tage;
	}

	public void setTage(int tage) {
		this.tage = tage;
	}
	public void setTage(String tage) {
		this.tage = Integer.parseInt(tage);
	}

	public String getTimage() {
		return timage;
	}

	public void setTimage(String timage) {
		this.timage = timage;
	}

	public String getTinfo() {
		return tinfo;
	}

	public void setTinfo(String tinfo) {
		this.tinfo = tinfo;
	}

	public String getTphone() {
		return tphone;
	}

	public void setTphone(String tphone) {
		this.tphone = tphone;
	}

	public String getTmail() {
		return tmail;
	}

	public void setTmail(String tmail) {
		this.tmail = tmail;
	}

}
