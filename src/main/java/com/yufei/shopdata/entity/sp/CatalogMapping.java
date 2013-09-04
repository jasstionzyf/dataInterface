package com.yufei.shopdata.entity.sp;

import java.io.Serializable;

import com.yufei.pfw.entity.Entity;





public class CatalogMapping extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//表示某个网站的某个分类的字符串
private String catalogId=null;
//表示属于某个网站
private int mallId;

//自己的特价分类id

private long spCatalogId;



public String getCatalogId() {
	return catalogId;
}

public void setCatalogId(String catalogId) {
	this.catalogId = catalogId;
}

public int getMallId() {
	return mallId;
}

public void setMallId(int mallId) {
	this.mallId = mallId;
}

public long getSpCatalogId() {
	return spCatalogId;
}

public void setSpCatalogId(long spCatalogId) {
	this.spCatalogId = spCatalogId;
}
}
