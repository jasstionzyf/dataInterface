package com.yufei.shopdata.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.yufei.utils.CommonUtil;

@XmlRootElement
public class AmazoneFetchInfoList {
private List<AmazoneFetchInfo> amazoneFetchInfos=new ArrayList<>();

public AmazoneFetchInfoList() {
	super();
	// TODO Auto-generated constructor stub
}

public List<AmazoneFetchInfo> getAmazoneFetchInfos() {
	return amazoneFetchInfos;
}
@XmlElementWrapper(name="amazoneFetchInfos")
@XmlElement(name="amazoneFetchInfo")
public void setAmazoneFetchInfos(List<AmazoneFetchInfo> amazoneFetchInfos) {
	this.amazoneFetchInfos = amazoneFetchInfos;
}
public static void main(String[] args){
	AmazoneFetchInfoList amazoneFetchInfoList=new AmazoneFetchInfoList();
	List<AmazoneFetchInfo> amazoneFetchInfos=new ArrayList<>();
	AmazoneFetchInfo amazoneFetchInfo = new AmazoneFetchInfo("http://www.amazon.cn/s/ref=sr_pg_3?rh=n%3A888465051%2Ck%3A%E7%AC%94%E8%AE%B0%E6%9C%AC&page={}&keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&ie=UTF8&qid=1365670841", 10, "/dp/(.*?)/", null);
	amazoneFetchInfo.setPageNumberSpace(1);
	amazoneFetchInfo.setTaobanCatalogId(112L);
	amazoneFetchInfos.add(amazoneFetchInfo);
	amazoneFetchInfoList.setAmazoneFetchInfos(amazoneFetchInfos);
	try {
		CommonUtil.generateXmlForObject(amazoneFetchInfoList, AmazoneFetchInfoList.class, "F:\\runner\\amazone_fetch.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
