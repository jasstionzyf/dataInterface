package com.yufei.shopdata.entity.sp;

import com.yufei.pfw.entity.Entity;
import com.yufei.pfw.service.PfwService;

public class Mall extends Entity {

	private String name;

	private String logoUrl;

	private String description;

	private String link;

	private int priority;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Mall() {
		super();
		// TODO Auto-generated constructor stub
	}
public static void main(String[] args) {
	Mall mall=new Mall();
	/*mall.setDescription("天猫");
	mall.setLink("http://www.tmall.com/");
	mall.setName("天猫");
	mall.setLogoUrl("http://img.taoban.com/resources/images/malllogo/tmalll.gif");*/
	mall.setName("亚马逊");
	mall.setLink("http://www.amazon.cn/");
	mall.setLogoUrl("http://img.taoban.com/resources/images/malllogo/amazon_cn.gif");
	mall.setDescription("亚马逊");
	PfwService.pfwService.save(mall);
}
}
