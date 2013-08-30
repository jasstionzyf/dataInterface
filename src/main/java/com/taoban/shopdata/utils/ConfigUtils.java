package com.taoban.shopdata.utils;

import com.yufei.utils.XPathUtil;

/**
 * @author jasstion
 * 下午3:53:24
 * 获取一些可以轻易改动的系统相关属性
 */
public class ConfigUtils {
	public static String getVlaue(String xpath){
		String value=null;
		try {
			value=XPathUtil.getNodeTextByXPath(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"), xpath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
           return null;
            }
		return value;
	}
	public static String getUrlForCommitUpdatedPrice(){
		String urlForCommitUpdatedPrice=null; 
		
		//String configFilePath=CommonUtil.getJarParentPath(AmazonUserInfo.class)+System.getProperty("file.separator")+"config.xml";
		try {
			urlForCommitUpdatedPrice=XPathUtil.getNodeTextByXPath(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"), "//urlForCommitUpdatedPrice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
           return null;
            }
		return urlForCommitUpdatedPrice;

	}
	public static String getUrlForGetItemInfo(){
		String urlForGetItemInfo=null; 

	//	String configFilePath=CommonUtil.getJarParentPath(AmazonUserInfo.class)+System.getProperty("file.separator")+"config.xml";
		
		try {
			urlForGetItemInfo=XPathUtil.getNodeTextByXPath(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"), "//urlForGetItemInfo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
           return null;
            }
		return urlForGetItemInfo;

	}
	public static String getRequestInterval(){
		String urlForGetItemInfo=null; 

	//	String configFilePath=CommonUtil.getJarParentPath(AmazonUserInfo.class)+System.getProperty("file.separator")+"config.xml";
		try {
			urlForGetItemInfo=XPathUtil.getNodeTextByXPath(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"), "//requestInterval");
		} catch (Exception e) {
			// TODO Auto-generated catch block
           return null;
            }
		return urlForGetItemInfo;

	}




}
