package com.yufei.shopdata.entity.sp;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import com.yufei.pfw.entity.Entity;

/**
 * @author jasstion
 *
 */

public class Sp extends Entity implements Serializable{
	/**
	 * for mongo db
	 */
	
	public static final Long serialVersionUID = 5510200638128934442L;
	@Indexed(unique=true)
	private String link=null;
	@Indexed
	private Long parentCategoryId; 
	

	
	private Long findTime;
	private Long updateTime;
	@Indexed
	private Long catalogId;
	
	private Integer  orderedNumber;
	@Indexed(unique=true)
	private String spName=null;
	@Transient
	private String catalogName=null;
	
	
	/**
	 * 原价
	 */
	private Double oprice;
	/**
	 * 	店铺名称
	 */
	@Indexed

	private String shopName=null;
	private Integer reviewCount;
	/**
	 * 是否下架
	 */
	private Boolean undercarriage=false;
	

	private Boolean hidden=false;
	private Double price;
	private Double discount;
	/**
	 * 	商品图片链接地址
	 */
	private String pictureUrl;
	private  Long begainTime;
	private Long endTime;
	/**
	 * 搜索优先级
	 */
	private Integer  priority=-1;	
	private Boolean displayed=false;
	private Boolean checked;
    @Transient
    private Date findDate;
    @Transient
    private Date updatedDate;
    @Transient
    private Date displayedDate;
    
  
	private Long displayedTime=0L;
	public Boolean isUndercarriage() {
		return undercarriage;
	}
	public void setUndercarriage(Boolean undercarriage) {
		this.undercarriage = undercarriage;
	}
	/**
	 * true表示已删除，false表示正常，默认false
	 */
	private Boolean deleted=false;
	/**
	 * stockHave==true表示有库存，false表示无库存
	 */
	private Boolean stockHave=true;
    private Double vipPrice;
    private Long mallId;
    @Indexed(unique=true)
    private String mallItemId;
	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	
	public Long getMallId() {
		return mallId;
	}
	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}
	public String getMallItemId() {
		return mallItemId;
	}
	
	public Long getBegainTime() {
		return begainTime;
	}
	public void setBegainTime(Long begainTime) {
		this.begainTime = begainTime;
	}
	public Boolean getUndercarriage() {
		return undercarriage;
	}
	public Boolean getStockHave() {
		return stockHave;
	}
	public void setMallItemId(String mallItemId) {
		this.mallItemId = mallItemId;
	}
	public Double getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}
	public Boolean isStockHave() {
		return stockHave;
	}
	public void setStockHave(Boolean stockHave) {
		this.stockHave = stockHave;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Long getDisplayedTime() {
		return displayedTime;
	}
	public void setDisplayedTime(Long displayedTime) {
		this.displayedTime = displayedTime;
	}
	public Date getFindDate() {
		return findDate;
	}
	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}


	
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}



	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	  public Date getDisplayedDate() {
			return displayedDate;
		}
		public void setDisplayedDate(Date displayedDate) {
			this.displayedDate = displayedDate;
		}


		public Integer getOrderedNumber() {
		return orderedNumber;
	}
	public void setOrderedNumber(Integer orderedNumber) {
		this.orderedNumber = orderedNumber;
	}
	public Double getOprice() {
		return oprice;
	}
	public Boolean isChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Boolean getDisplayed() {
		return displayed;
	}
	public void setDisplayed(Boolean displayed) {
		this.displayed = displayed;
	}
	public Long getEndTime() {
		return endTime;
	}
	
		/**
		 * 是否被显示（前台）
		 */



		public String getSpName() {
			return spName;
		}
		public void setSpName(String spName) {
			this.spName = spName;
		}

	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public Sp() {
		super();
		// TODO Auto-generated constructor stub
	}




	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		
		this.pictureUrl = pictureUrl;
	}
	
	public Long getFindTime() {
		return findTime;
	}
	public void setFindTime(Long findTime) {
		this.findTime = findTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

/**
 * 服装>内衣
 */
	@Transient
private String catalogs=null;

public String getCatalogs() {
	return catalogs;
}
public void setCatalogs(String catalogs) {
	this.catalogs = catalogs;
}

/**
 * 是否当天
 */
@Transient
private Integer isCurrentDay;

public Integer getIsCurrentDay() {
	return isCurrentDay;
}
public void setIsCurrentDay(Integer isCurrentDay) {
	this.isCurrentDay = isCurrentDay;
}

/**
 * 本商品的点击数
 */
private Integer clickCount;
public Integer getClickCount() {
	return clickCount;
}
public void setClickCount(Integer clickCount) {
	this.clickCount = clickCount;
}

}
