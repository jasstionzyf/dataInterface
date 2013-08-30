package com.taoban.DataInterface;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.UmpPromotionGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.UmpPromotionGetResponse;

public class APITest {
   
	private static Log mLog = LogFactory.getLog(APITest.class);


      
    
    public static void main(String[] args) {
    	 String url = "http://gw.api.taobao.com/router/rest";
    	 //21466012    6bda04503275df95490431e14eab1d5e 
    	 String appkey = "21466012";
            String appSecret = "36d98ee632070d5cc8a093bb36f8900d";
        //  String appkey = "21431371";
       //   String appSecret = "ed3ec7d1737b3110650d718deee2e4a5";
    	  TaobaoClient client=new DefaultTaobaoClient(url, appkey, appSecret,"xml");
    	  //tmall.selected.items.search
          /*ItemcatsGetRequest req=new ItemcatsGetRequest();
          
         // req.setFields("cid,parent_cid,name,is_parent");
         req.setParentCid(0L);
        //  req.setCids("18957,19562,");
          try {
			ItemcatsGetResponse response = client.execute(req);
			
		    System.out.print(response.getBody());

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    	  TmallSelectedItemsSearchRequest req=new TmallSelectedItemsSearchRequest();
    	  req.setCid(50019780L);
    	  
    	 

    	  TmallSelectedItemsSearchResponse res=null;
    	  try {
			  res=client.execute(req);
			  System.out.print(res.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	//  再调用taobao.item.get获取商品详情，再调用taobao.taobaoke.items.convert 获取有佣金的淘客推广链接
    	  ItemGetRequest req=new ItemGetRequest();
    	  req.setFields("item_img,props_name,title,price,desc,url,detail_url,title,nick,desc,cid,pic_url,num,valid_thru,delist_time,list_time");

    	//  req.setTrackIid("20799800694_track_11116");
    	  req.setNumIid(18483933355L); 
    	  try {
			ItemGetResponse response = client.execute(req);
			System.out.print(response.getBody()+"\n");
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  /*String str="num_iid,item_img,props_name,title,price,desc,url,detail_url,title,nick,desc,cid,pic_url,num,valid_thru";
    	  ItemsListGetRequest req=new ItemsListGetRequest();
    	  req.setFields(str);
    	  //一次不超过20个
    	  req.setNumIids("14000661842,17113453848123");
    	  try {
  			ItemsListGetResponse response = client.execute(req);
  			System.out.print(response.getBody());
  		} catch (ApiException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}*/
    	  //优惠价格
    	  UmpPromotionGetRequest req1=new UmpPromotionGetRequest();
    	  req1.setItemId(18483933355L);
    	  
    	  try {
			UmpPromotionGetResponse response = client.execute(req1);
			System.out.print(response.getBody());
			mLog.info(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  /*//店铺信息查询
    	  ShopGetRequest req=new ShopGetRequest();
    	  req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
    	  req.setNick("能良数码专营店");
    	  try {
			ShopGetResponse response = client.execute(req);
			System.out.print(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    /*DataRepository dataRepository=	(DataRepository) AppUtil.getBeanFromBeanContainer(DataRepository.class);
    MallItem mallItem=new MallItem();
    mallItem.setReviewCount(1000);
    mallItem.setItemId(12345L);
    dataRepository.save(mallItem);
    MallItem mallItem2=dataRepository.queryEntity("reviewCount", 1000, MallItem.class);
    System.out.print(mallItem2.getId());
    System.out.print(mallItem2.getReviewCount());
    System.out.print(dataRepository.queryListEntity("reviewCount", 1000, MallItem.class).size());*/
   /* String str=null;
    try {
		str=CommonUtil.getHtmlContent("http://list.tmall.com/search_product.htm?fromF5=&totalPage=34&cat=50024399&sort=s&style=g&vmarket=0&area_code=110000&jumpto=10", false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.print(str);
    List<String> mallItemIds=null;
    mallItemIds=PatternUtils.getListStrByRegex(str, "mallstItemId=(.*?)&");
    for(String itemId:mallItemIds){
    	  req=new ItemGetRequest();
   	  req.setFields("item_img,props_name,title,price,desc");
   	//  req.setTrackIid("20799800694_track_11116");
   	  System.out.print("开始获取商品:"+itemId+"的具体信息"+"\n");
   	  req.setNumIid(Long.parseLong(itemId));
   	  try {
			ItemGetResponse response = client.execute(req);
			System.out.print(response.getBody()+"\n");
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //获取查询商品总页数
    String totalNumber;
    try {
		String str1=CommonUtil.getHtmlContent("http://list.tmall.com//search_product.htm?q=%B1%CA%BC%C7%B1%BE&type=p&style=&cat=all", false);
		mLog.info(str1);
		mLog.info(PatternUtils.getStrByRegex(str1,"ui-page-s-len\">(.*?)</b>"));
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	*/		/*HiberOperations ho= new MongoDbOps();
    	Map map=new HashMap();
    	map.put("spName like ", "夏日");
    	System.out.print(ho.find(Sp.class, map).size());*/
    	/*String strByRegex="http://img02.taobaocdn.com/bao/uploaded/i2/T1fnrdXl8XXXcmC.c5_060300.jpg_160x160.jpg";
		strByRegex=strByRegex.replaceAll("jpg.*?.jpg","");
		strByRegex+="jpg";
		System.out.print(strByRegex);
    	*/

    	  
    }
    }