package com.yufei.shopdata.urlGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yufei.dataget.utils.HtmlUtil;
import com.yufei.shopdata.amazone.AmazoneUserInfoPool;
import com.yufei.shopdata.entity.AmazonUserInfo;
import com.yufei.shopdata.entity.UserInfoList;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.ExceptionUtil;


/**
 * @author jasstion
 * 下午2:18:56
 * 用于产生各个商城数据接口需要提交的信息比如：具体url等
 */
public class ShopDataUrlGenerator {
	private static Log mLog = LogFactory.getLog(ShopDataUrlGenerator.class);
    private static final String ENDPOINT ="webservices.amazon.cn";//"ecs.amazonaws.ca";
   public static String gernateAmazonUrl(AmazonUserInfo amazonUserInfo,String operation,String responseGroup,List<String> itemids,Map<String,String> externalParas){
	   StringBuffer amazonUrl=new StringBuffer();
	   SignedRequestsHelper helper;
		try {
	        helper = SignedRequestsHelper.getInstance(ENDPOINT, amazonUserInfo.getAWS_ACCESS_KEY_ID(), amazonUserInfo.getAWS_SECRET_KEY());
	    } catch (Exception e) {
	    	mLog.info(ExceptionUtil.getExceptionDetailsMessage(e));
	    	return null;
	    }
		
	    
	    

	    
	    Map<String, String> params = new HashMap<String, String>();
	 
		String itemIdStr=CommonUtil.LinkStringWithSpecialSymbol(itemids, ",");

	    params.put("Service", "AWSECommerceService");
	    params.put("Version", "2011-08-01");
	    params.put("Operation", operation);
	    params.put("ItemId", itemIdStr);
	    params.put("IdType", "ASIN");
	    params.put("ResponseGroup", responseGroup);
	    params.put("AssociateTag", amazonUserInfo.getAssociateTag());
	    //会覆盖之前设置的已有的参数
	    if(externalParas!=null&&externalParas.size()!=0){
	    	params.putAll(externalParas);
	    }

	    

	    amazonUrl.append(helper.sign(params));
		return amazonUrl.toString();
   }
 public static AmazonUserInfo  getRandomUserInfo(){
	 UserInfoList infoList=null;;
	if(infoList==null){
		    infoList=(UserInfoList) CommonUtil.getObjectFromXml(Thread.currentThread().getContextClassLoader().getResourceAsStream("userInfoList.xml"), UserInfoList.class);

	   }
       List<AmazonUserInfo> amazonUserInfos=infoList.getUserInfoList();
	   if(CommonUtil.isEmptyOrNull(amazonUserInfos)){
    	   mLog.info("请配置Amazon接口对应的用户信息！");
    	   return null;
       }
       int randomIndex=new Random().nextInt(amazonUserInfos.size());
       return amazonUserInfos.get(randomIndex);
   }
 /*
*//**
 * @param itemid
 * @return
 * 以随机的方式利用系统提供的账户中的一个来进行链接的签名
 *//*
   
public static String getAmazonItemLookupUrl(String itemid){
	StringBuffer requestUrl=new StringBuffer();
	   
     * Set up the signed requests helper 
     
    SignedRequestsHelper helper;
    AmazonUserInfo amazonUserInfo = getRandomUserInfo();
	try {
        helper = SignedRequestsHelper.getInstance(ENDPOINT, amazonUserInfo.getAWS_ACCESS_KEY_ID(), amazonUserInfo.getAWS_SECRET_KEY());
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    
    

    
    Map<String, String> params = new HashMap<String, String>();
    params.put("Service", "AWSECommerceService");
    params.put("Version", "2011-08-01");
    params.put("Operation", "ItemLookup");
    params.put("ItemId", itemid);
    params.put("IdType", "ASIN");
    params.put("ResponseGroup", "OfferSummary");
    params.put("AssociateTag", amazonUserInfo.getAssociateTag());
   // params.put("SearchIndex", "All");

    

    requestUrl.append(helper.sign(params));
	return requestUrl.toString();
}
public static String getAmazonItemLookupUrl(List<String> itemids){
	StringBuffer requestUrl=new StringBuffer();
	   
     * Set up the signed requests helper 
     
    SignedRequestsHelper helper;
    AmazonUserInfo amazonUserInfo = getRandomUserInfo();
	try {
        helper = SignedRequestsHelper.getInstance(ENDPOINT, amazonUserInfo.getAWS_ACCESS_KEY_ID(), amazonUserInfo.getAWS_SECRET_KEY());
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    
    
	String itemIdStr=CommonUtil.LinkStringWithSpecialSymbol(itemids, ",");
    StringBuffer itemIds=new StringBuffer();
    Map<String, String> params = new HashMap<String, String>();
    params.put("Service", "AWSECommerceService");
    params.put("Version", "2011-08-01");
    params.put("Operation", "ItemLookup");
    params.put("ItemId", itemIdStr);
    params.put("IdType", "ASIN");
    params.put("ResponseGroup", "OfferSummary");
    params.put("AssociateTag", amazonUserInfo.getAssociateTag());
   // params.put("SearchIndex", "All");

    

    requestUrl.append(helper.sign(params));
	return requestUrl.toString();
}
public static String getAmazonItemLookupUrl(List<String> itemids,AmazonUserInfo amazonUserInfo){
	StringBuffer requestUrl=new StringBuffer();
	   
     * Set up the signed requests helper 
     
    SignedRequestsHelper helper;
   // AmazonUserInfo amazonUserInfo = getRandomUserInfo();
	try {
        helper = SignedRequestsHelper.getInstance(ENDPOINT, amazonUserInfo.getAWS_ACCESS_KEY_ID(), amazonUserInfo.getAWS_SECRET_KEY());
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    
    
	String itemIdStr=CommonUtil.LinkStringWithSpecialSymbol(itemids, ",");
    StringBuffer itemIds=new StringBuffer();
    Map<String, String> params = new HashMap<String, String>();
    params.put("Service", "AWSECommerceService");
    params.put("Version", "2011-08-01");
    params.put("Operation", "ItemLookup");
    params.put("ItemId", itemIdStr);
    params.put("IdType", "ASIN");
    params.put("ResponseGroup", "OfferSummary");
    params.put("AssociateTag", amazonUserInfo.getAssociateTag());
   // params.put("SearchIndex", "All");

    

    requestUrl.append(helper.sign(params));
	return requestUrl.toString();
}
    public static String getAmazonItemLookupUrl(String itemid,String idType,String ResponseGroup,String Operation ){
    	StringBuffer requestUrl=new StringBuffer();
    	   
         * Set up the signed requests helper 
         
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        

        
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("Operation", Operation);
        params.put("ItemId", itemid);
        params.put("IdType", idType);
        params.put("ResponseGroup", ResponseGroup);
        params.put("AssociateTag", "onmygoing-23");
       // params.put("SearchIndex", "All");

        

        requestUrl.append(helper.sign(params));
    	return requestUrl.toString();
    }*/
    public static void main(String[] args){
    	String Operation="ItemSearch",SearchIndex="All",Keywords="harry_potter";
    	StringBuffer requestUrl=new StringBuffer();
 	  
    	AmazoneUserInfoPool amazoneUserInfoPool=AmazoneUserInfoPool.getInstance();
     SignedRequestsHelper helper = null;
     try {
         AmazonUserInfo amazonUserInfo = amazoneUserInfoPool.getAmazonUserInfo();
		helper = SignedRequestsHelper.getInstance(ENDPOINT, amazonUserInfo.getAWS_ACCESS_KEY_ID(), amazonUserInfo.getAWS_SECRET_KEY());
     } catch (Exception e) {
         e.printStackTrace();
         
     }
     
     

     
     Map<String, String> params = new HashMap<String, String>();
     params.put("Service", "AWSECommerceService");
     params.put("Version", "2011-08-01");
    // params.put("Operation", Operation);
    // params.put("BrowseNode", "106200071");
     params.put("Operation", "ItemLookup");
     params.put("ItemId", "B003R7KVWA");
     params.put("IdType", "ASIN");
     
   
     params.put("Keywords", "笔记本");
 /*    params.put("Operation", "ItemSearch");
     params.put("SearchIndex", "Electronics");
     params.put("Keywords", "笔记本");
     
   
     params.put("Condition", "All");
     params.put("ItemPage", "10");
     params.put("BrowseNode", "BrowseNode");*/


     //
     params.put("ResponseGroup", "Large");
     params.put("AssociateTag", "onmygoing-23");

     
     requestUrl.append(helper.sign(params));
 //   mLog.info(requestUrl);
  String info=HtmlUtil.getHtmlContent(requestUrl.toString(), 2, 0, false);
  mLog.info(info);
    /* String url="http://www.amazon.cn/mn/search/ajax/ref=sr_pg_4?rh=n%3A888465051%2Cn%3A106200071%2Cn%3A888483051&page=10&bbn=106200071&ie=UTF8&qid=1365052109&fromHash=%2Fref%3Dsr_pg_3%3Frh%3Dn%253A888465051%252Cn%253A106200071%252Cn%253A888483051%26page%3D3%26bbn%3D106200071%26ie%3DUTF8%26qid%3D1365052104&section=BTF&fromApp=gp%2Fsearch&fromPage=results&version=2";
   try {
	mLog.info(StringUtil.ascii2Native(CommonUtil.getHtmlContent(url, false)));
	mLog.info(StringUtil.ascii2Native("&#32852;&#24819;"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/
    }
}
