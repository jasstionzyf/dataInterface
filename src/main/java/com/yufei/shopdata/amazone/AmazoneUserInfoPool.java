package com.yufei.shopdata.amazone;

import java.util.List;
import java.util.Queue;

import org.eclipse.jetty.util.ArrayQueue;

import com.yufei.shopdata.entity.AmazonUserInfo;
import com.yufei.shopdata.entity.UserInfoList;
import com.yufei.utils.CommonUtil;




/**
 * @author jasstion
  2013-4-12下午12:34:03
 *类似于数据库连接池
 */
public class AmazoneUserInfoPool {
	private static AmazoneUserInfoPool amazoneUserInfoPool=null;
	public synchronized  static AmazoneUserInfoPool   getInstance(){
	if(amazoneUserInfoPool==null){
		amazoneUserInfoPool=new AmazoneUserInfoPool();
		 UserInfoList infoList=null;;
			
				    infoList=(UserInfoList) CommonUtil.getObjectFromXml(Thread.currentThread().getContextClassLoader().getResourceAsStream("userInfoList.xml"), UserInfoList.class);

			   
		       List<AmazonUserInfo> amazonUserInfos=infoList.getUserInfoList();
		       for (AmazonUserInfo amazonUserInfo : amazonUserInfos) {
		    	   amazoneUserInfoPool.freeUserInfos.add(amazonUserInfo)
		    	   ;
			}
	}
	return amazoneUserInfoPool;
	}
	private AmazoneUserInfoPool() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Queue<AmazonUserInfo> freeUserInfos=new ArrayQueue<>(50);//(Queue<AmazonUserInfo>) Collections.synchronizedCollection(new );
    public synchronized void releaseAmazonUserInfo(AmazonUserInfo amazonUserInfo){
    	freeUserInfos.add(amazonUserInfo);
    }
	public synchronized AmazonUserInfo getAmazonUserInfo(){
    	return freeUserInfos.poll();
    }
}
