package com.yufei.shopdata.entity.sp;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

import com.yufei.pfw.entity.Entity;


public class SpCatalog extends Entity implements Serializable{
	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public SpCatalog(Long id,Long parentCategoryId, String name, String description) {
		
		super();
		this.id=id;
		this.parentCategoryId = parentCategoryId;
		this.name = name;
		this.description = description;
	}

	public SpCatalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private Long parentCategoryId;
	@Indexed(unique=true)
	private String name=null;
	
	




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description=null;
}
