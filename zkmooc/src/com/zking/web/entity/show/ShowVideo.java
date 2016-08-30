package com.zking.web.entity.show;

public class ShowVideo {
	private String vuid; // 视频uid
	private String cuid; // 视频uid
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	private String vname; // 视频名
	private String cname; // 所属课程名

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
