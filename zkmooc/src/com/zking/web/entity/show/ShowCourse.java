package com.zking.web.entity.show;

public class ShowCourse {
	private String cuid;		//课程uid
	private String cname;		//课程名
	private String cinfo;		//课程简介
	private String ccname; 		// 课程所属类别
	private String ccuid; 		// 课程所属类别

	public String getCcuid() {
		return ccuid;
	}

	public void setCcuid(String ccuid) {
		this.ccuid = ccuid;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCinfo() {
		return cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
}
