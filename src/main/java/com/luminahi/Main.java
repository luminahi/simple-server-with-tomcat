package com.luminahi;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.luminahi.servlets.StaticServlet;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("", null);

        Tomcat.addServlet(context, "staticApp", new StaticServlet());
        context.addServletMappingDecoded("/", "staticApp");

        tomcat.start();
        tomcat.getServer().await();
    }
}