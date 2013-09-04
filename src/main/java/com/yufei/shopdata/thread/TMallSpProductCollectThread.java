package com.yufei.shopdata.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.yufei.dataget.utils.HtmlUtil;
import com.yufei.pfw.service.PfwService;
import com.yufei.shopdata.entity.sp.CatalogMapping;
import com.yufei.shopdata.entity.sp.Sp;
import com.yufei.shopdata.entity.sp.SpCatalog;
import com.yufei.shopdata.linkGetter.ExternalLinksGetter;
import com.yufei.shopdata.linkGetter.ExternalMongodbLinksGetter;
import com.yufei.shopdata.utils.ConfigUtils;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.ExceptionUtil;
import com.yufei.utils.PatternUtils;

/**
 * @author jasstion 专门根据查询链接获取商品简单信息 上午11:55:24
 */
public class TMallSpProductCollectThread implements Runnable {

	private static final Log mLog = LogFactory.getLog(TMallSpProductCollectThread.class);
	long connectIntervalTime=0;
	private PfwService pfwService=PfwService.pfwService;
    private List<String> externalLinks;

	public TMallSpProductCollectThread(List<String> externalLinks) {
		super();
		this.externalLinks = externalLinks;
	}

	@Override
	public void run() {
       
	
		//String test="http://list.tmall.com/search_product.htm?start_price=20&end_price=80&post_fee=-1&miaosha=1&zk_type=0&search_condition=16&cat=50031555&sort=s&style=g&vmarket=0&from=sn_1_cat-qp&q=%BB%AF%D7%B1%C6%B7";
		//externalLinks=new ArrayList<>();
		//externalLinks.add(test)
		//;
	
		if(!CommonUtil.isEmptyOrNull(externalLinks)){
			Iterator<String> it=externalLinks.iterator();
		while(it.hasNext()){
			parseLink(it.next());
		}
			


		}
	}

	private void parseLink(String link) {
		Document doc;
		String htmlTxt = null;
		htmlTxt = HtmlUtil.getHtmlContent(link, 3, connectIntervalTime, false);
		if (CommonUtil.isEmptyOrNull(link)) {
			mLog.info("访问链接:" + link + "出错！");
		}
		if (htmlTxt == null) {
			return;
		}

		doc = Jsoup.parse(htmlTxt);
		// ￥68.99 (4.34折) ￥159.00
		Elements productPriceElements = doc.getElementsByClass("productPrice");
		Elements productShopElements = doc.getElementsByClass("productShop");
		Elements productTitleElements = doc.getElementsByClass("productTitle");
		// productImg-wrap
		Elements productImgElements_1 = doc.getElementsByClass("productImg");

		Elements productImgElements = doc.getElementsByClass("productImg-wrap");

		Elements productStatusElements = doc
				.getElementsByClass("productStatus");

		mLog.info("开始访问目录链接:" + link + "");
		if (CommonUtil.isEmptyOrNull(productPriceElements)) {
			mLog.info("解析目录链接:" + link + "获取价格列表失败！");
			return;

		}
		String productBody = null;
		// false 表示保存
		boolean isSaved = true;
		// Element element:productPriceElements
		String url = null, title = null, pictureUrl = null, shopName = null;
		Double price = 0.00, oprice = 0.00, discount = 0.00;
		Double reviewcount = 0.00;
		Double orderNumber = 0.00;
		for (int i = 0; i < productPriceElements.size(); i++) {
			Element element = productPriceElements.get(i);
			try {
				productBody = element.text();

				productBody = productBody.replaceAll("¥", "")
						.replaceAll("\\(", "").replaceAll("\\)", "");
				mLog.info(productBody);
				String[] strs = productBody.split(" ");
				if (strs.length != 3) {
					continue;
				}
				try {
					discount = Double.parseDouble(PatternUtils.getStrByRegex(
							strs[1].trim(), "(.*?)折"));
					price = Double.parseDouble(strs[0].trim());
					oprice = Double.parseDouble(strs[2].trim());

				} catch (Exception e) {
					// 特殊情况 750元/500g
					String temp = strs[2].trim().replaceAll("元/.*?g", "");
					oprice = Double.parseDouble(temp);

				}
				if (i < productShopElements.size()) {
					Element shopElement = productShopElements.get(i);
					shopName = shopElement.text();

				}
				boolean isWan = false;
				// 月销量:2753|累计评价:1.4万
				if (i < productStatusElements.size()) {
					Element statusElement = productStatusElements.get(i);
					String text = statusElement.text();
					text = text.trim();
					boolean iswan1 = false;
					boolean iswan2 = false;
					int p = text.indexOf("|");
					if (text.lastIndexOf("万") > p) {
						iswan2 = true;
						text = text.substring(0, text.lastIndexOf("万"));
					}
					if (text.contains("万")) {
						iswan1 = true;
					}

					text = text.replaceAll("万", "");

					mLog.info(text);
					if (!CommonUtil.isEmptyOrNull(text)) {
						try {
							String strByRegex = PatternUtils.getStrByRegex(
									text, "月销量:(.*?)\\|");
							orderNumber = Double.parseDouble(strByRegex);
							if (iswan1) {
								orderNumber = orderNumber * 10000;
							}
						} catch (Exception e) {

						}

						String reviewStr = PatternUtils.getStrByRegex(text,
								"\\|累计评价:(.*)");

						try {
							reviewcount = Double.parseDouble(reviewStr);
							if (iswan2) {
								reviewcount = reviewcount * 10000;
							}

						} catch (Exception e) {

						}

						// 设置link title//设置图片
						if (i < productTitleElements.size()) {
							Element titleElement = productTitleElements.get(i);
							title = titleElement.text();
							url = PatternUtils.getStrByRegex(
									titleElement.html(), "href=\"(.*?)\"");

						}

					}

				}

				Map parametersMap = new HashMap();
				parametersMap.put("spName", title);
				Sp sp = null;

				List<Sp> sps =pfwService.queryList("spName", title, Sp.class
						);
				String mallItemId=PatternUtils.getStrByRegex(url.replace("amp;",""), "id=(.*?)&");
				if(sps==null||sps.isEmpty()){
					sps=pfwService.queryList("mallItemId", mallItemId, Sp.class);
				}
				if (!CommonUtil.isEmptyOrNull(sps)) {
					isSaved = false;
					sp = sps.get(0);
					sp.setUpdateTime(System.currentTimeMillis());
					
					
				} else {
					sp = new Sp();
					sp.setMallId(1L);
					sp.setMallItemId(mallItemId);
					sp.setUpdateTime(System.currentTimeMillis());
					sp.setFindTime(System.currentTimeMillis());
					// save or update
					long spCatalogId = getSpCatalogId(link);
					if(spCatalogId!=-1){
						sp.setCatalogId(spCatalogId);
						SpCatalog spCatalog = pfwService.query("id", spCatalogId, SpCatalog.class);//ho.find(SpCatalog.class, spCatalogId);

						sp.setParentCategoryId(spCatalog.getParentCategoryId());
					}

				}
				if (CommonUtil.isEmptyOrNull(sp.getPictureUrl())) {
					if (!CommonUtil.isEmptyOrNull(productImgElements)) {
						if (i < productImgElements.size()) {
							Element pictureElement = productImgElements.get(i);
							// data-ks-lazyload
							String strByRegex = PatternUtils.getStrByRegex(
									pictureElement.html(),
									"src=\"(.*?)\"!data-ks-lazyload=\"(.*?)\"");
							if (CommonUtil.isEmptyOrNull(strByRegex)) {

								mLog.info("图片获取失败，上级链接是:" + link + ",商品链接是"
										+ url + "");
							} 

							else{
								//http://img02.taobaocdn.com/bao/uploaded/i2/T1fnrdXl8XXXcmC.c5_060300.jpg_160x160.jpg
								strByRegex=strByRegex.replaceAll("_[\\d]{3}x[\\d]{3}.*","");
								
								sp.setPictureUrl(strByRegex);
								}


					}
					}
					if (!CommonUtil.isEmptyOrNull(productImgElements_1)) {
						if (i < productImgElements.size()) {
							Element pictureElement = productImgElements_1
									.get(i);
							// data-ks-lazyload
							String strByRegex = PatternUtils.getStrByRegex(
									pictureElement.html(),
									"src=\"(.*?)\"!data-ks-lazyload=\"(.*?)\"");
							if (CommonUtil.isEmptyOrNull(strByRegex)) {

								mLog.info("图片获取失败，上级链接是:" + link + ",商品链接是"
										+ url + "");
							} else {
								strByRegex=strByRegex.replaceAll("_[\\d]{3}x[\\d]{3}.*","");
								sp.setPictureUrl(strByRegex);
							}
						}
					}

				}
				sp.setPrice(price);
				sp.setDiscount(discount);
				sp.setOprice(oprice);
				sp.setShopName(shopName);
				sp.setReviewCount(reviewcount.intValue());

				sp.setLink(url.replace("amp;",""));
				sp.setSpName(title);
				sp.setOrderedNumber(orderNumber.intValue());
				if (isSaved) {
					// 获取图片
					// 5s
					String productDetailContent = HtmlUtil.getHtmlContent(url,
							3, 7, false);

					if (!CommonUtil.isEmptyOrNull(productDetailContent)) {
						String pu = PatternUtils.getStrByRegex(
								productDetailContent,
								"J_ImgBooth[\\s\\S\\r\\n]{0,}?src=\"(.*?)\"");
						if (!CommonUtil.isEmptyOrNull(pu)) {
							sp.setPictureUrl(pu);
						}

					}

				}
				 sp.setDisplayed(true);
                 sp.setDisplayedTime(System.currentTimeMillis());
				pfwService.save(sp);
			}catch (Exception e) {
				mLog.info(ExceptionUtil.getExceptionDetailsMessage(e));
			}

		}

	}

	private long getSpCatalogId(String url) {
		// TODO Auto-generated method stub
		long spCatalogId = 0;
		List<CatalogMapping> catalogMappings = null;
		catalogMappings = pfwService.queryList(null, null, CatalogMapping.class);
		if(CommonUtil.isEmptyOrNull(catalogMappings)){
			return -1;
		}
		
		for (CatalogMapping ca : catalogMappings) {
			if (url.contains(ca.getCatalogId())) {
				spCatalogId = ca.getSpCatalogId();
				break;
			}

		}
		return spCatalogId;
	}

	public static void main(String[] args){
		ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(6);

		PfwService pfwService=PfwService.pfwService;
		 //亚马逊商品信息获取线程
	      scheduledExecutorService.scheduleAtFixedRate(new AmazonSpProductCollectThread(), 1,60*60*10, TimeUnit.SECONDS);
		List<String> externalLinks=null;
		ExternalLinksGetter externalLinksGetter=new ExternalMongodbLinksGetter();
	 externalLinks=externalLinksGetter.getExternalLinks();
	 int threadNumbers=3;
	 List<List<String>> lists=CommonUtil.splitKs(externalLinks, externalLinks.size()/threadNumbers);
		//定期想spider-server 获取价格更行任务
		long updatedPriority=Long.parseLong(ConfigUtils.getVlaue("//tmallSp/updatedPriority"));


       for(List<String> list:lists){
   		scheduledExecutorService.scheduleAtFixedRate(new TMallSpProductCollectThread(list), 1,updatedPriority, TimeUnit.SECONDS);

       }
      /* //索引线程
      scheduledExecutorService.scheduleAtFixedRate(new IndexThread(), 1,60*60*3, TimeUnit.SECONDS);*/
     



		


		
	}
	}

