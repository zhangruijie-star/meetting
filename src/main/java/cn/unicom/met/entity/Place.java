package cn.unicom.met.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 会议地点实体类
 * @author Administrator *
 */
public class Place {
	private int uuid;//编号
	private String address;//会议地点

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
