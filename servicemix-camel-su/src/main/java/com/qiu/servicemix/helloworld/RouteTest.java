/**  
 * Filename:    RouteTest.java  
 * Description:   
 * Copyright:   Copyright (c)2011  
 * Company:     AsiaInfo-Linkage, Inc.
 * @author:     qiubo  
 * @version:    1.0  
 * Create at:   2012-3-30 下午07:08:43  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2012-3-30    qiubo             1.0    Create  
 */
package com.qiu.servicemix.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.bus.CXFBusImpl;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;

/**
 * @ClassName: RouteTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-30 下午07:08:43
 * 
 */
public class RouteTest {
	RouteTest() {
	}

	public void createProxy() throws Exception {
		//加载camel-context.xml文件，获取上下文
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"camel-context.xml");
		//获取CamelContext信息
		CamelContext camelContext = (CamelContext) ctx.getBean("camel");
		//配置信息，可能根据参数来获取多个cxfEndpoint达到动态生成被代理WS服务
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<beans xmlns=\"http://www.springframework.org/schema/beans\""
				+ "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
				+ "       xmlns:cxf=\"http://camel.apache.org/schema/cxf\""
				+ "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
				+ "       http://camel.apache.org/schema/cxf http://camel.apache.org/schema/cxf/camel-cxf-2.5.0.xsd\">"
				+ "<import resource=\"classpath:META-INF/cxf/cxf.xml\" />"
				+ "<import resource=\"classpath:META-INF/cxf/cxf-extension-soap.xml\" />"
				+ "<import resource=\"classpath:META-INF/cxf/cxf-extension-http-jetty.xml\" />"
				+ "   <cxf:cxfEndpoint id=\"helloworldService\" endpointName=\"s:HelloWorldImplPort\" serviceName=\"s:HelloWorldImplService\""
				+ "                     wsdlURL=\"classpath:etc/HelloWorlds.wsdl\" xmlns:s=\"http://impl.helloworld.servicemix.qiu.com/\"/>"
				+ "</beans>";
		//将上面的xml文件里的信息动态生成并注册bean
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();
		XmlBeanFactory factory = new XmlBeanFactory(new ByteArrayResource(
				xml.getBytes()));
		acf.registerBeanDefinition("helloworldService",
				factory.getMergedBeanDefinition("helloworldService"));
		System.out.println("OK");
		//添加CamelContext路由信息，可以根据条件动态配置多个路由信息进行代理。
		camelContext.addRoutes(new RouteBuilder() {

			public void configure() throws Exception { // TODO
				from("cxf:bean:helloworldService?dataFormat=MESSAGE")
						.id("routetest")
						.to("http://localhost:9000/realService?throwExceptionOnFailure=true");
			}
		});
		camelContext.start();
		camelContext.startRoute("routetest");
		camelContext.stopRoute("routetest");
		camelContext.removeRoute("routetest");

	}

	public static void main(String[] args) throws Exception {
		RouteTest routeTest = new RouteTest();
		routeTest.createProxy();
	}

}
