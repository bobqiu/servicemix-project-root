/**  
  * Filename:    Server.java  
  * Description:   
  * Copyright:   Copyright (c)2011  
  * Company:     AsiaInfo-Linkage, Inc.
  * @author:     qiubo  
  * @version:    1.0  
  * Create at:   2012-3-21 下午11:14:59  
  *  
  * Modification History:  
  * Date         Author      Version     Description  
  * ------------------------------------------------------------------  
  * 2012-3-21    qiubo             1.0    Create  
  */  
package com.qiu.server;

import javax.xml.ws.Endpoint;

import com.qiu.webservice.HelloWorldImpl;

/**
 * @ClassName: Server
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-21 下午11:14:59
 * 
 */
public class Server {
    private HelloWorldImpl service = new HelloWorldImpl();
    private String url="http://localhost:9083/HelloWold";
    private Endpoint endpoint;

    public void start() throws Exception {
        System.out.println("Starting real web service...");
        endpoint = Endpoint.publish(url, service);
        System.out.println("Started real web service at: " + url);
    }

    public void stop() throws Exception {
        if (endpoint != null) {
            System.out.println("Stopping real webservice...");
            endpoint.stop();
            System.out.println("Stopped real webservice.");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start();
	}
}
