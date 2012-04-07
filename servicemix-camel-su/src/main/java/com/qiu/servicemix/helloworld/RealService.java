/**  
  * Filename:    RealService.java  
  * Description:   
  * Copyright:   Copyright (c)2011  
  * Company:     AsiaInfo-Linkage, Inc.
  * @author:     qiubo  
  * @version:    1.0  
  * Create at:   2012-3-26 下午07:30:33  
  *  
  * Modification History:  
  * Date         Author      Version     Description  
  * ------------------------------------------------------------------  
  * 2012-3-26    qiubo             1.0    Create  
  */  
package com.qiu.servicemix.helloworld;

import javax.xml.ws.Endpoint;

import com.qiu.servicemix.helloworld.impl.HelloWorldImpl;

/**
 * @ClassName: RealService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-26 下午07:30:33
 * 
 */
public class RealService {
	private String url;
	
	private HelloWorldImpl helloWorldImpl = new HelloWorldImpl();
	private Endpoint endpoint;
	
	public void start(){
		System.out.println("Starting realService+++++++++++++++");
		endpoint = Endpoint.publish(url, helloWorldImpl);
		System.out.println("Starting realService+++++++++++++++at::"+url);
	}
	public void stop(){
		endpoint.stop();
		System.out.println("Stop realService+++++++++++++++");
	}

	/**
	 * @return url
	 */
	
	public String getUrl() {
		return url;
	}

	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	

}
