package com.taoban.DataInterface;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.ware.WareInfoByInfoRequest;
import com.jd.open.api.sdk.response.ware.WareInfoByInfoSearchResponse;
import com.yufei.utils.DateUtil;
import com.yufei.utils.EncryptUtil;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
   
    public static void main(String[] args) {
         final String SERVER_URL = "http://gw.api.360buy.com/routerjson";
         // App Key：C3F6EE56178E40A8175BA299C5072E89
        //  App Secret 9e753cfee61148b39f43d0502b4e3bda
    	//jingdong
          String appSecret="2d0d4cd3c0ba4cfd83f892fcb175d014";
          String method="jingdong.ware.get";
          String appKey="A8414657B51F165D75ABB173B72C3CB4";
          String sign=null;
          String timestamp=DateUtil.getCurrentDateString(DateUtil.DATE_TIME);
          String apiVersion="2.0";
         // access_token,app_key,method,timestamp,v
          //ket+value...
          
          
          
          StringBuffer signBuffer=new StringBuffer();
          //app_key,method,timestamp,v
          signBuffer.append(appSecret).append(appKey).append(method).append(timestamp).append(apiVersion).append(appSecret);
    	 sign=  EncryptUtil.md5(signBuffer.toString()).toUpperCase();
    	 System.out.print(sign);
          JdClient client = new DefaultJdClient(SERVER_URL,
        		  sign,appKey,appSecret);
    			WareInfoByInfoRequest wareInfoByInfoRequest= new WareInfoByInfoRequest();
    			wareInfoByInfoRequest.setPage("1");
    			wareInfoByInfoRequest.setPageSize("10");
    			wareInfoByInfoRequest.setStartPrice("100");
    			wareInfoByInfoRequest.setEndPrice("1000");
    			
    			try {
					WareInfoByInfoSearchResponse res = client.execute(wareInfoByInfoRequest);
					System.out.print(res.getMsg());
				} catch (JdException e) {
					// TODO Auto-generated catch block
				System.out.print(e.getMessage());
				}
    	/*try {
			System.out.print(CommonUtil.getHtmlContent("http://ju.taobao.com/tg/home.htm?spm=608.2177197.0.319.S9Mz2O&id=17113453848", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	//="1363957505371" data-targettime="1363996799000">
    	
    	 
    	
    }
}
