/**  
  * Filename:    HelloWorldImpl.java  
  * Description:   
  * Copyright:   Copyright (c)2011  
  * Company:     AsiaInfo-Linkage, Inc.
  * @author:     qiubo  
  * @version:    1.0  
  * Create at:   2012-3-26 下午07:21:23  
  *  
  * Modification History:  
  * Date         Author      Version     Description  
  * ------------------------------------------------------------------  
  * 2012-3-26    qiubo             1.0    Create  
  */  
package com.qiu.servicemix.helloworld.impl;

import javax.jws.WebService;

import com.qiu.servicemix.helloworld.interfaces.HelloWorld;

/**
 * @ClassName: HelloWorldImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-26 下午07:21:23
 * 
 */
@WebService(endpointInterface = "com.qiu.servicemix.helloworld.interfaces.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	/** (非 Javadoc)
	 * <p>Description: </p>
	 * @param s
	 * @return
	 * @see com.qiu.servicemix.helloworld.interfaces.HelloWorld#sayHi(java.lang.String)
	 */
	public String sayHi(String s) {
		// TODO Auto-generated method stub
		return "hello world 3.26::"+s;
	}

}
