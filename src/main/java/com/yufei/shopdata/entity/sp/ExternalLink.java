package com.yufei.shopdata.entity.sp;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

import com.yufei.pfw.entity.Entity;




public class ExternalLink extends Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Indexed
    private String link;
    private Long catalogId= new Long(0);
public ExternalLink() {
		super();
		// TODO Auto-generated constructor stub
	}

public Long getCatalogId() {
	return catalogId;
}

public void setCatalogId(Long catalogId) {
	this.catalogId = catalogId;
}

public ExternalLink(String link) {
	super();
	this.link = link;
}

public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}

}
