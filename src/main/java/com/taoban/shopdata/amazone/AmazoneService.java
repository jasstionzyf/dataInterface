package com.taoban.shopdata.amazone;

import java.util.ArrayList;
import java.util.List;

import com.taoban.shopdata.entity.AmazonUserInfo;
import com.taoban.shopdata.entity.sp.Sp;
import com.taoban.shopdata.urlGenerator.ShopDataUrlGenerator;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.HtmlUtil;
import com.yufei.utils.PatternUtils;
import com.yufei.utils.XPathUtil;

public class AmazoneService {
public static Sp getSpInfo(String url) throws Exception{
	Sp sp=new Sp();
	String dp=PatternUtils.getStrByRegex(url, "dp/(.*?)/");
    if(CommonUtil.isEmptyOrNull(dp)){
    	return null;
    }
	sp.setMallItemId(dp);
	
 
		
		///dp/B003R7KVWA/
		String operation="ItemLookup";
		String responseGroup="Large";
		List<String> itemids=new ArrayList<>();
		itemids.add(dp);
		AmazonUserInfo amazonUserInfo = AmazoneUserInfoPool.getInstance().getAmazonUserInfo();
		String requestUrl=ShopDataUrlGenerator.gernateAmazonUrl(amazonUserInfo, operation, responseGroup, itemids, null);
		
		AmazoneUserInfoPool.getInstance().releaseAmazonUserInfo(amazonUserInfo);
		String responseXml=HtmlUtil.getHtmlContent(requestUrl.toString(), 2, 0, false);

		
		
		//goodDetailDes=XPathUtil.getValueOfNode(itemNode, "EditorialReviews");
		
		String price=XPathUtil.getNodeTextByXPath(responseXml, "//SalePrice/FormattedPrice"); 
		if(price==null){
			 price=XPathUtil.getNodeTextByXPath(responseXml, "//Price/FormattedPrice");
		}
		String oprice=XPathUtil.getNodeTextByXPath(responseXml, "//ListPrice/FormattedPrice"); 
        if(oprice==null){
        	oprice=XPathUtil.getNodeTextByXPath(responseXml, "//Price/FormattedPrice");

        }
		
		
		
        
		String link=XPathUtil.getNodeTextByXPath(responseXml, "//DetailPageURL");
		String spName=XPathUtil.getNodeTextByXPath(responseXml, "//Title");
		String pictureUrl=XPathUtil.getNodeTextByXPath(responseXml, "//LargeImage/URL");
		
		sp.setLink(link);
		if(!CommonUtil.isEmptyOrNull(price)){
			sp.setPrice(Double.parseDouble(price.trim().replace("￥", "").replace(",", "").replace("$", "")));

		}
        if(!CommonUtil.isEmptyOrNull(oprice)){
	       sp.setOprice(Double.parseDouble(oprice.trim().replace("￥", "").replace(",", "").replace("$", "")));

		}
		sp.setFindTime(System.currentTimeMillis());
		sp.setDisplayed(sp.getDisplayed());
		sp.setPictureUrl(pictureUrl);
		if(sp.getOprice()!=null){
			
		
		sp.setDiscount(Math.floor(sp.getPrice()/sp.getOprice()*10));
		}
		sp.setSpName(spName);
      
	
	return sp;
}
public static void main(String[] args) throws Exception {
	String spUrl="http://www.amazon.cn/%E4%BD%B3%E8%89%BA%E7%B2%BE%E5%93%81%E5%BE%A1%E8%97%A4%E5%87%89%E5%B8%AD%E5%8F%8C%E4%BA%BA%E4%B8%89%E4%BB%B6%E5%A5%97-150cm-195cm%E5%AE%BD%E8%BE%B9%E5%A4%AA%E9%98%B3%E8%8A%B1/dp/B007RSTCXY/ref=sr_1_1?ie=UTF8&qid=1373632111&sr=8-1&keywords=%E5%87%89%E5%B8%AD";
	Sp sp0=AmazoneService.getSpInfo(spUrl);
	System.out.print(sp0.getLink());

}
}
