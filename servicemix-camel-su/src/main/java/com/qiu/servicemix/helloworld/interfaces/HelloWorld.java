/**  
  * Filename:    HelloWorld.java  
  * Description:   
  * Copyright:   Copyright (c)2011  
  * Company:     AsiaInfo-Linkage, Inc.
  * @author:     qiubo  
  * @version:    1.0  
  * Create at:   2012-3-26 下午07:12:13  
  *  
  * Modification History:  
  * Date         Author      Version     Description  
  * ------------------------------------------------------------------  
  * 2012-3-26    qiubo             1.0    Create  
  */  
package com.qiu.servicemix.helloworld.interfaces;

import javax.jws.WebService;

/**
 * @ClassName: HelloWorld
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-26 下午07:12:13
 * 
 */
@WebService
public interface HelloWorld {
	public String sayHi(String s);
}
