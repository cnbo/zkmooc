package com.zking.web.entity.course;

/**
 * 课程类别
 * 
 * @author 胡博
 * 
 */
public class CourseCategory {
	private String ccuid; // 课程类别uid
	private String ccname; // 课程名

	public CourseCategory() {

	}

	public CourseCategory(String ccuid, String ccname) {
		super();
		this.ccuid = ccuid;
		this.ccname = ccname;
	}

	public String getCcuid() {
		return ccuid;
	}

	public void setCcuid(String ccuid) {
		this.ccuid = ccuid;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

}
