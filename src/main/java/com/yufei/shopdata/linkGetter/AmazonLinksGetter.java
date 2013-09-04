package com.yufei.shopdata.linkGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.yufei.dataget.entity.PaginationRule;
import com.yufei.dataget.entity.UrlParameter;
import com.yufei.dataget.utils.HtmlUtil;
import com.yufei.pfw.service.PfwService;
import com.yufei.shopdata.entity.sp.ExternalLink;
import com.yufei.utils.CommonUtil;

public class AmazonLinksGetter  implements ExternalLinksGetter{
	private static final Log mLog = LogFactory.getLog(AmazonLinksGetter.class);
	private PfwService pfwService=PfwService.pfwService;

//PfwService pfwService=(PfwService) AppUtil.getBeanFromBeanContainer(MongodbPfwService.class);
	
	public List<String> getExternalLinks() {
		// TODO Auto-generated method stub
		List<ExternalLink> externalLinks;
		Map map=new HashMap();
		map.put("link:like", "amazon");
		externalLinks = pfwService.query(map,ExternalLink.class);
		externalLinks=new ArrayList<>();
		ExternalLink e = new ExternalLink("http://www.amazon.cn/s/ref=sr_st?qid=1372525300&rh=n%3A816482051&__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&sort=salesrank");
		
		externalLinks.add(e);
	
		List<String> readyUrls=new ArrayList<>();
		
		List<String> pageUrlTemplates=new ArrayList<String>();
		
		for(ExternalLink externalLink:externalLinks){
			
			String link =externalLink.getLink();
			Long catalogId=externalLink.getCatalogId();
				
					String html=HtmlUtil.getHtmlContent(link, 5,1,false);
					if(CommonUtil.isEmptyOrNull(html)){
						mLog.info("针对目录链接"+link+"获取失败！");
						continue;
					}
					Document doc=null;
					doc = Jsoup.parse(html);
					Elements elements=doc.getElementsByClass("pagnNext");
					String pageUrlTemplate=elements.attr("href");
					
					if(CommonUtil.isEmptyOrNull(pageUrlTemplate)){
						pageUrlTemplate=link;
					}
				    // pageUrlTemplate=pageUrlTemplate.replaceAll("n=[\\d]{0,}?&", "n=180");
					else{
				     pageUrlTemplate=pageUrlTemplate.replaceAll("(page=[\\d]{0,}?)&", "page={}&");
				     pageUrlTemplate=CommonUtil.normalizeUrl(pageUrlTemplate, link);
				     pageUrlTemplate+="&catalogId="+catalogId+"";
				     mLog.info("获取目录链接模板:"+pageUrlTemplate+"");
					}

					pageUrlTemplates.add(pageUrlTemplate);
					//q=%C4%DA%D2%C2


			}
		

		//}
		for(String pageUrlTemplate:pageUrlTemplates){
			if(pageUrlTemplate.contains("{}")){
				PaginationRule paginationRule = new PaginationRule();
			    paginationRule.setPaginationTemplate(pageUrlTemplate);
			    UrlParameter urlParameter=new UrlParameter();
			    urlParameter.setBegainPagNumber(0);
			    urlParameter.setPagNumberSpace(1);
			    urlParameter.setParameterType("Integer");
			    urlParameter.setParameterValue(String.valueOf(20));
			    paginationRule.getUrlParameters().add(urlParameter);
				List<String> urls=HtmlUtil.generateUrlsByPaginationRule(paginationRule); 
				readyUrls.addAll(urls);
			}
			else{
				readyUrls.add(pageUrlTemplate)
;			}
		
			
		}
		return readyUrls;
	}
public static void main(String[] args){
	List<String> s=new AmazonLinksGetter().getExternalLinks();
	for(String str:s){
		mLog.info(str);
	}
}
}
