package com.yufei.shopdata.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AmazonUserInfo {
private String associateTag=null;
private String AWS_ACCESS_KEY_ID=null;

private String AWS_SECRET_KEY=null;

public String getAssociateTag() {
	return associateTag;
}

public AmazonUserInfo() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "AmazonUserInfo [associateTag=" + associateTag
			+ ", AWS_ACCESS_KEY_ID=" + AWS_ACCESS_KEY_ID + ", AWS_SECRET_KEY="
			+ AWS_SECRET_KEY + "]";
}

@XmlElement
public void setAssociateTag(String associateTag) {
	this.associateTag = associateTag;
}

public String getAWS_ACCESS_KEY_ID() {
	return AWS_ACCESS_KEY_ID;
}
@XmlElement

public void setAWS_ACCESS_KEY_ID(String aWS_ACCESS_KEY_ID) {
	AWS_ACCESS_KEY_ID = aWS_ACCESS_KEY_ID;
}

public String getAWS_SECRET_KEY() {
	return AWS_SECRET_KEY;
}
@XmlElement

public void setAWS_SECRET_KEY(String aWS_SECRET_KEY) {
	AWS_SECRET_KEY = aWS_SECRET_KEY;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((AWS_ACCESS_KEY_ID == null) ? 0 : AWS_ACCESS_KEY_ID.hashCode());
	result = prime * result
			+ ((AWS_SECRET_KEY == null) ? 0 : AWS_SECRET_KEY.hashCode());
	result = prime * result
			+ ((associateTag == null) ? 0 : associateTag.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AmazonUserInfo other = (AmazonUserInfo) obj;
	if (AWS_ACCESS_KEY_ID == null) {
		if (other.AWS_ACCESS_KEY_ID != null)
			return false;
	} else if (!AWS_ACCESS_KEY_ID.equals(other.AWS_ACCESS_KEY_ID))
		return false;
	if (AWS_SECRET_KEY == null) {
		if (other.AWS_SECRET_KEY != null)
			return false;
	} else if (!AWS_SECRET_KEY.equals(other.AWS_SECRET_KEY))
		return false;
	if (associateTag == null) {
		if (other.associateTag != null)
			return false;
	} else if (!associateTag.equals(other.associateTag))
		return false;
	return true;
}


}
