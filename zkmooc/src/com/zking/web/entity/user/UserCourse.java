package com.zking.web.entity.user;

/**
 * 用户课程
 * 
 * @author 胡博
 * 
 */
public class UserCourse {
	private String ucuid; // 用户课程uid
	private String uuid; // 用户uid
	private String cuid; // 用户学习课程的uid

	public UserCourse() {

	}

	public UserCourse(String ucuid, String uuid, String cuid) {
		super();
		this.ucuid = ucuid;
		this.uuid = uuid;
		this.cuid = cuid;
	}

	public String getUcuid() {
		return ucuid;
	}

	public void setUcuid(String ucuid) {
		this.ucuid = ucuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

}
