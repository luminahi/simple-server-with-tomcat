package com.luminahi;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.luminahi.servlets.StaticServlet;

public class Main {
    
    
    public static void main(String[] args) throws LifecycleException {
        final String appDir = new File("").getAbsolutePath();
    
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File(appDir, "/target/tmp").getAbsolutePath());
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("", new File("").getAbsolutePath() + "/src/main/webapp");

        Tomcat.addServlet(context, "staticApp", new StaticServlet());
        context.addServletMappingDecoded("/", "staticApp");

        tomcat.start();
        tomcat.getServer().await();
    }
}