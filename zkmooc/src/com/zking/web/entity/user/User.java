package com.zking.web.entity.user;

/**
 * 用户
 * 
 * @author胡博
 * 
 */
public class User {
	private String uuid;			// 用户uid
	private String uname;			// 用户姓名
	private int uage;				//用户年龄
	private String unickName;		// 用户昵称
	private String uphone;			// 用户手机号
	private String umail;			// 用户邮箱
	private String usex;			// 用户性别
	private String upass;			// 用户密码
	private String address;			// 用户所在城市
	private String uimage;			// 用户头像
	private String uprofession;		// 用户职业

	public User() {

	}

	public User(String uuid, String uname, String unickName, String uphone,
			String umail, String usex, String upass, String address,
			String uimage, String uprofession, int uage) {
		super();
		this.uuid = uuid;
		this.uname = uname;
		this.unickName = unickName;
		this.uphone = uphone;
		this.umail = umail;
		this.usex = usex;
		this.upass = upass;
		this.address = address;
		this.uimage = uimage;
		this.uprofession = uprofession;
		this.uage = uage;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUnickName() {
		return unickName;
	}

	public void setUnickName(String unickName) {
		this.unickName = unickName;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

	public String getUprofession() {
		return uprofession;
	}

	public void setUprofession(String uprofession) {
		this.uprofession = uprofession;
	}

	public int getUage() {
		return uage;
	}

	public void setUage(int uage) {
		this.uage = uage;
	}

}
