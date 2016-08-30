package com.zking.web.entity.course;

/**
 * 课程
 * 
 * @author 胡博
 * 
 */
public class Course {
	private String cuid; // 课程uid
	private String cname; // 课程名称
	private String ccuid; // 课程所属类别uid
	private String tuid; // 课程所属教师uid
	private String cimage; // 课程图片
	private String cinfo; // 课程简要介绍

	public Course() {

	}

	public Course(String cuid, String cname, String ccuid, String tuid,
			String cimage, String cinfo) {
		super();
		this.cuid = cuid;
		this.cname = cname;
		this.ccuid = ccuid;
		this.tuid = tuid;
		this.cimage = cimage;
		this.cinfo = cinfo;
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

	public String getCcuid() {
		return ccuid;
	}

	public void setCcuid(String ccuid) {
		this.ccuid = ccuid;
	}

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public String getCimage() {
		return cimage;
	}

	public void setCimage(String cimage) {
		this.cimage = cimage;
	}

	public String getCinfo() {
		return cinfo;
	}

	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}

}
