package com.taoban.shopdata.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taoban.shopdata.entity.sp.Sp;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.UmpPromotionGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.UmpPromotionGetResponse;
import com.yufei.pfw.service.PfwService;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.DateUtil;
import com.yufei.utils.ExceptionUtil;
import com.yufei.utils.PatternUtils;
import com.yufei.utils.XPathUtil;

public class SpInfoUpdateThread implements Runnable {
	private static final Log mLog = LogFactory.getLog(SpInfoUpdateThread.class);

	private PfwService pfwService=PfwService.pfwService;


	@Override
	public void run() {
		
		long oneDayTime = 1 * 24 * 60 * 60 * 1000;
		long threeDatTime = 3 * oneDayTime;
		long thrityDayTime = 30 * oneDayTime;
		// 程序自动将上架时间超过三天的商品下架；

		// 程序自动将已下架的商品距离第一次收录时间超过30天的物理删除，（标志删除位）
		long currentTime = System.currentTimeMillis();
		long comparedTime = currentTime - thrityDayTime;
		Map parasMap = new HashMap();
		// 下架

		parasMap.put("displayed:=", false);
		// 大于30天

		parasMap.put("findTime:lt", comparedTime);
		
		pfwService.removeAll(parasMap, Sp.class);
		parasMap = new HashMap();
		parasMap.put("displayed:=", true);
		currentTime = System.currentTimeMillis();
		comparedTime = currentTime - threeDatTime;
		// 大于3天
		parasMap.put("displayedTime:bw","0-"+comparedTime+"");

	
	    Map<String, Object> setValuesMap=new HashMap();
	    setValuesMap.put("displayed", false);
		pfwService.updateAll(parasMap, setValuesMap, Sp.class);
	}

	public static void main(String[] args) {
		/*String strByRegex="http://img.taoban.com/upload/images/2013-04/2013-04-26/1366966335490_892.jpgjpg";
		strByRegex=strByRegex.replaceAll("(jpg.*?jpg)","");
		strByRegex+="jpg";
		System.out.print(strByRegex);*/
		


		ScheduledExecutorService scheduledExecutorService = Executors
				.newScheduledThreadPool(4);
		
		
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					


					
					
					String url = "http://gw.api.taobao.com/router/rest";
					String appkey = "21431371";
					String appSecret = "ed3ec7d1737b3110650d718deee2e4a5";
					TaobaoClient client = new DefaultTaobaoClient(url, appkey,
							appSecret, "xml");
					 PfwService pfwService=PfwService.pfwService;

					Map map = new HashMap<>();
					map.put("deleted:=", false);
					map.put("displayed:=", true);

				

					List<Sp> sps =pfwService.query(map, Sp.class) ;
					List<String> mallItemIds = new ArrayList<>();
					for (Sp sp : sps) {

						try {
							String link = sp.getLink();
							String num_id = PatternUtils.getStrByRegex(link,
									"id=(.*?)&");
							long mallItemId = Long.parseLong(num_id);

							double price;
							double oprice;
							int stockNum = -1;
							ItemGetRequest req = new ItemGetRequest();
							req.setFields("price,num,delist_time");
							req.setNumIid(mallItemId);
							try {
								ItemGetResponse response = client.execute(req);
								String oprice1 = XPathUtil.getNodeTextByXPath(
										response.getBody(), "//price");
								String stockNum1 = XPathUtil
										.getNodeTextByXPath(response.getBody(),
												"//num");
								String dlistTime=XPathUtil
										.getNodeTextByXPath(response.getBody(),
												"//delist_time");
								if(!CommonUtil.isEmptyOrNull(dlistTime)){
									long dlistt=DateUtil.getDate(dlistTime, DateUtil.DATE_TIME).getTime();
									if(dlistt<System.currentTimeMillis()){
										//下架
										sp.setUndercarriage(true);
									}
								}

								oprice = Double.parseDouble(oprice1);
								stockNum = Integer.parseInt(stockNum1);
								if (oprice > 0) {
									sp.setOprice(oprice);
								}
								if (stockNum <= 0) {
									sp.setStockHave(false);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								mLog.info(ExceptionUtil
										.getExceptionDetailsMessage(e));
							}

							try {
								UmpPromotionGetRequest req1 = new UmpPromotionGetRequest();
								req1.setItemId(mallItemId);

								UmpPromotionGetResponse response = client
										.execute(req1);
								String body = response.getBody();
								String nodeTextByXPath = XPathUtil
										.getNodeTextByXPath(body,
												"//item_promo_price");
								if(CommonUtil.isEmptyOrNull(nodeTextByXPath)){
									//促销已经结束
									sp.setPrice(sp.getOprice());
									mLog.debug("商品:"+sp.getMallItemId()+"促销已经结束，修改状态不显示！");
									sp.setDisplayed(false);

								}
								String endTime=XPathUtil
										.getNodeTextByXPath(response.getBody(),
												"//end_time");
								if(!CommonUtil.isEmptyOrNull(endTime)){
									long dlistt=DateUtil.getDate(endTime, DateUtil.DATE_TIME).getTime();
									if(dlistt<System.currentTimeMillis()){
										sp.setPrice(sp.getOprice());
										mLog.debug("商品:"+sp.getMallItemId()+"促销已经结束，修改状态不显示！");
										sp.setDisplayed(false);
									}
								}
								price = Double.parseDouble(nodeTextByXPath);

								if (price > 0) {
									sp.setPrice(price);
									mLog.debug("价格更新为"+price+"");
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								// 没有促销价格或者促销已经取消，价格设置为原价
								mLog.info(ExceptionUtil.getExceptionDetailsMessage(e));
								sp.setPrice(sp.getOprice());

								map = new HashMap<>();
								map.put("spId", sp.getId());
								//ho.delete(CompetitiveSp.class, map);
							} finally {
								pfwService.save(sp);

							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							mLog.info(ExceptionUtil
									.getExceptionDetailsMessage(e));
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					mLog.info(ExceptionUtil.getExceptionDetailsMessage(e));

				}
			}
		}, 1, 1000 * 60 * 60 * 8, TimeUnit.SECONDS);
		/*long oneDayTime = 1 * 24 * 60 * 60 * 1000;
		long threeDatTime = 3 * oneDayTime;
		long thrityDayTime = 30 * oneDayTime;
		// 程序自动将上架时间超过三天的商品下架；

		// 程序自动将已下架的商品距离第一次收录时间超过30天的物理删除，（标志删除位）
		long currentTime = System.currentTimeMillis();
		long comparedTime = currentTime - thrityDayTime;
		Map parasMap = new HashMap();
		// 下架

		parasMap.put("displayed:=", false);
		// 大于30天

		parasMap.put("findTime:lt", comparedTime);
		
		PfwService pfwService=PfwService.pfwService;
		pfwService.removeAll(parasMap, Sp.class);
		parasMap = new HashMap();
		parasMap.put("displayed:=", true);
		currentTime = System.currentTimeMillis();
		comparedTime = currentTime - threeDatTime;
		// 大于3天
		parasMap.put("displayedTime:bw","0-"+comparedTime+"");

	
	    Map<String, Object> setValuesMap=new HashMap();
	    List<Sp> sps=pfwService.query(parasMap, Sp.class);
	    System.out.print(sps.size());
		PfwService pfwService=PfwService.pfwService;
		Map map=null;
		List<Sp> sps=pfwService.query(map, Sp.class);
		for(Sp sp:sps){
			sp.setLink(sp.getLink().replace("&amp;", "&"));
			pfwService.save(sp);
		}*/
	  	}
}