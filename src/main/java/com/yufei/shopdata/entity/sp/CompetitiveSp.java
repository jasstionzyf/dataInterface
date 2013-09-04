package com.yufei.shopdata.entity.sp;


import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.yufei.pfw.entity.Entity;




public class CompetitiveSp extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1851536787183258556L;
	final static String ninenineType="9.9";
	
	private long spId;
	
	/**
	 * 表示精品类型
	 */
	private String type=null;
	
	/**
	 * 优先级（仅仅限于某个精品内）
	 */
	private int priority=0;
	private long time;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public CompetitiveSp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getSpId() {
		return spId;
	}
	public void setSpId(long spId) {
		this.spId = spId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public CompetitiveSp(long spId) {
		super();
		this.spId = spId;
	}


	@Transient
	private Sp sp=null;
	public Sp getSp() {
		return sp;
	}
	public void setSp(Sp sp) {
		this.sp = sp;
	}

}
