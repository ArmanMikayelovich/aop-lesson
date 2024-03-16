package com.mikayelovich.config;

import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Component
public class WebAppInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create a new application context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // Register configuration class
        context.register(WebConfig.class);

        // Set the lifecycle of the application context to be managed by the servlet context
        context.setServletContext(servletContext);

        // Add the Spring DispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));

        // Set load on startup priority
        servlet.setLoadOnStartup(1);

        // Add URL mappings for the DispatcherServlet
        servlet.addMapping("/");


    }
}
