package com.yufei.shopdata.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AmazoneFetchInfo {
public AmazoneFetchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
public AmazoneFetchInfo(String paginationTemplate,
			Integer fetchedPageNumber, String asinRegex, String totalCountRegex) {
		super();
		this.paginationTemplate = paginationTemplate;
		this.fetchedPageNumber = fetchedPageNumber;
		this.asinRegex = asinRegex;
		this.totalCountRegex = totalCountRegex;
	}
private String paginationTemplate=null;
private Integer fetchedPageNumber=0;
private Integer pageNumberSpace=0;

private String asinRegex=null;
private String totalCountRegex;
private Long taobanCatalogId;
public Long getTaobanCatalogId() {
	return taobanCatalogId;
}
@XmlElement
public void setTaobanCatalogId(Long taobanCatalogId) {
	this.taobanCatalogId = taobanCatalogId;
}
public String getPaginationTemplate() {
	return paginationTemplate;
}
@XmlElement
public void setPaginationTemplate(String paginationTemplate) {
	this.paginationTemplate = paginationTemplate;
}
public Integer getFetchedPageNumber() {
	return fetchedPageNumber;
}
public Integer getPageNumberSpace() {
	return pageNumberSpace;
}
public void setPageNumberSpace(Integer pageNumberSpace) {
	this.pageNumberSpace = pageNumberSpace;
}
@XmlElement
public void setFetchedPageNumber(Integer fetchedPageNumber) {
	this.fetchedPageNumber = fetchedPageNumber;
}
public String getAsinRegex() {
	return asinRegex;
}
@XmlElement
public void setAsinRegex(String asinRegex) {
	this.asinRegex = asinRegex;
}
public String getTotalCountRegex() {
	return totalCountRegex;
}
@XmlElement
public void setTotalCountRegex(String totalCountRegex) {
	this.totalCountRegex = totalCountRegex;
}

}
