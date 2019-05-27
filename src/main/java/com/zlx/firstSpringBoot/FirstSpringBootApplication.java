package com.zlx.firstSpringBoot;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("com.zlx.firstSpringBoot")
@EnableSwagger2Doc
public class FirstSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FirstSpringBootApplication.class, args);


        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx.getBean("tomcatServletWebServerFactory");
            int port = tomcatServletWebServerFactory.getPort();
            String contextPath = tomcatServletWebServerFactory.getContextPath();
            System.out.println("http://" + host + ":" + port + contextPath + "/");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
