/**  
 * Filename:    RouteBuilderTest.java  
 * Description:   
 * Copyright:   Copyright (c)2011  
 * Company:     AsiaInfo-Linkage, Inc.
 * @author:     qiubo  
 * @version:    1.0  
 * Create at:   2012-3-31 下午10:53:23  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2012-3-31    qiubo             1.0    Create  
 */
package com.qiu.servicemix.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;

/**
 * @ClassName: RouteBuilderTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qiubo
 * @date 2012-3-31 下午10:53:23
 * 
 */
public class RouteBuilderTest extends RouteBuilder {

	/**
	 * (非 Javadoc)
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @throws Exception
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"camel-context.xml");
		CamelContext camelContext = (CamelContext) ctx.getBean("camel");
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
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();
		XmlBeanFactory factory = new XmlBeanFactory(new ByteArrayResource(
				xml.getBytes()));
		acf.registerBeanDefinition("helloworldService",
				factory.getMergedBeanDefinition("helloworldService"));
		System.out.println("OK");
		from("cxf:bean:helloworldService?dataFormat=MESSAGE")
				.id("routetest")
				.to("http://localhost:9000/realService?throwExceptionOnFailure=true");
	}

}
