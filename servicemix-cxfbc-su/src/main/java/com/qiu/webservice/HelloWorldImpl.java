
package com.qiu.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "com.qiu.webservice.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

