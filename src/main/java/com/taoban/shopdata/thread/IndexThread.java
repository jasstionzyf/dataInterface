package com.taoban.shopdata.thread;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taoban.shopdata.entity.sp.Sp;
import com.yufei.pfw.service.PfwService;
import com.yufei.service.index.IndexService;
import com.yufei.utils.ExceptionUtil;

/**
 * @author jasstion Email:zhao-0244@qq.com 2013-7-7 上午9:59:22
 *         每隔三个小时对这三个小时内采集到的商品继续索引处理
 */
public class IndexThread implements Runnable {
	IndexService indexService = IndexService.getInstance();
	private static final Log mLog = LogFactory.getLog(IndexThread.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		PfwService pfwService = PfwService.pfwService;
		HashMap parametersMap = new HashMap();
		parametersMap.put("findTime:gt", System.currentTimeMillis() - 1000 * 60
				* 60* 3);
		List<Sp> sps = pfwService.query(parametersMap, Sp.class);
		for (Sp sp : sps) {

			try {
				indexService.index(sp.getId(), sp.getSpName());
				mLog.debug("index "+sp.getSpName()+""+sp.getId().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				mLog.debug(ExceptionUtil.getExceptionDetailsMessage(e));
			}
			pfwService.save(sp);

		}
	}
	public static void main(String[] args){
		new IndexThread().run();
	}
}
