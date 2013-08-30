package com.taoban.shopdata.thread;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taoban.shopdata.amazone.AmazoneService;
import com.taoban.shopdata.entity.sp.Sp;
import com.yufei.extractor.entity.UfLink;
import com.yufei.pfw.service.PfwService;
import com.yufei.utils.CommonUtil;
import com.yufei.utils.ExceptionUtil;
import com.yufei.utils.HtmlUtil;
import com.yufei.utils.PatternUtils;

/**
 * @author jasstion 专门根据查询链接获取商品简单信息 上午11:55:24
 */
public class AmazonSpProductCollectThread implements Runnable {

	private static final Log mLog = LogFactory.getLog(AmazonSpProductCollectThread.class);
	long connectIntervalTime=0;
	private PfwService pfwService=PfwService.pfwService;
    

	@Override
	public void run() {
		 HashMap parametersMap = new HashMap();
		  parametersMap.put("mallId:=", 2L);
		  parametersMap.put("isProcessed:=", false);

		  List<UfLink> uflinks=PfwService.pfwService.query(parametersMap, UfLink.class);
		  for(UfLink ufLink:uflinks){
			  try {
				Sp sp=AmazoneService.getSpInfo(ufLink.getLink());
				sp.setMallId(ufLink.getMallId());
				sp.setDisplayed(true);
				ufLink.setIsProcessed(true);
				pfwService.save(ufLink);
				pfwService.save(sp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
	           mLog.info(ExceptionUtil.getExceptionDetailsMessage(e));

			}
			  
		  }
		/*List<String> externalLinks=null;
		ExternalLinksGetter externalLinksGetter=new AmazonLinksGetter();
	 externalLinks=externalLinksGetter.getExternalLinks();
		//String test="http://list.tmall.com/search_product.htm?start_price=20&end_price=80&post_fee=-1&miaosha=1&zk_type=0&search_condition=16&cat=50031555&sort=s&style=g&vmarket=0&from=sn_1_cat-qp&q=%BB%AF%D7%B1%C6%B7";
		//externalLinks=new ArrayList<>();
		//externalLinks.add(test)
		//;
		if(!CommonUtil.isEmptyOrNull(externalLinks)){
			Iterator<String> it=externalLinks.iterator();
		while(it.hasNext()){
			try{
			parseLink(it.next());
			}catch(Exception e){
				ExceptionUtil.getExceptionDetailsMessage(e);
			}
		}
			


		}*/
	}

	private void parseLink(String link) {
		String catalogId=PatternUtils.getStrByRegex(link, "&catalogId=(.*)");
		link=link.replaceAll("&catalogId=(.*)", "");
		String htmlTxt = null;
		htmlTxt = HtmlUtil.getHtmlContent(link, 3, connectIntervalTime, false);
		if (CommonUtil.isEmptyOrNull(link)) {
			mLog.info("访问链接:" + link + "出错！");
		}
		if (htmlTxt == null) {
			return;
		}
		List<String> urls=PatternUtils.getListStrByRegex(htmlTxt, "href=\"(.*?)\"");
		Sp sp=null;
		for(String url:urls){
			try {
				sp=AmazoneService.getSpInfo(url);
				if(sp!=null){
					
					sp.setCatalogId(Long.parseLong(catalogId));
                    sp.setDisplayed(true);
                    sp.setDisplayedTime(System.currentTimeMillis());
					pfwService.save(sp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
	}

	public static void main(String[] args){
		new AmazonSpProductCollectThread().run();
		
	}
	}

