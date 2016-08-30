package com.zking.web.entity.course;

/**
 * 视频
 * 
 * @author 胡博
 * 
 */
public class Video {
	private String vuid; // 视频uid
	private String vname; // 视频名称
	private String vpath; // 视频存储路径
	private String cuid; // 所属课程的uid

	public Video() {

	}

	public Video(String vuid, String vname, String vpath, String cuid) {
		super();
		this.vuid = vuid;
		this.vname = vname;
		this.vpath = vpath;
		this.cuid = cuid;
	}

	public String getVuid() {
		return vuid;
	}

	public void setVuid(String vuid) {
		this.vuid = vuid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVpath() {
		return vpath;
	}

	public void setVpath(String vpath) {
		this.vpath = vpath;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

}
