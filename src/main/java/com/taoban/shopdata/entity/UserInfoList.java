package com.taoban.shopdata.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author jasstion
 * 下午6:17:55
 * 配置数据库接口需要的用户名密码等一些验证信息
 */
@XmlRootElement
public class UserInfoList {
	private List<AmazonUserInfo> userInfoList=null;

	public List<AmazonUserInfo> getUserInfoList() {
		return userInfoList;
	}

	public UserInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}
    @XmlElement
	public void setUserInfoList(List<AmazonUserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

}
