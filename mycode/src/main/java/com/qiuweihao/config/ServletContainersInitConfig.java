package com.qiuweihao.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{SpringConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{SpringMvcConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}

/*
3）ServletContainersInitConfig（或 AbstractDispatcherServletInitializer）是“容器外的启动/注册配置”

这是你觉得最不一样的那个，区别在于：

它做的事

代替 web.xml

在 Tomcat 启动时注册 DispatcherServlet

告诉 Tomcat：哪些 URL 交给 DispatcherServlet

创建 SpringMVC 容器（以及可选 Root 容器）

谁在用？

**Tomcat（Servlet 容器）**在启动 Web 应用时用它。

配置对象是什么？

它配置的不是 Bean，而是：

Servlet（DispatcherServlet）

Servlet 映射（/）

WebApplicationContext 的初始化

✅ 简单说：它是“把 SpringMVC 接到 Tomcat 上”的桥。
 */


public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {


    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfig.class);
        return ctx;
    }
}


/*“ServletContainersInitConfig：教 Tomcat 怎么把请求交给 DispatcherServlet，并启动 SpringMVC 容器”是什么意思？

关键点：Controller 想处理浏览器请求，必须有一个“入口”接住 HTTP 请求。这个入口就是 DispatcherServlet。

Tomcat 只认识 Servlet，它不知道什么 Controller、@GetMapping。

所以需要一个类（你的 ServletContainersInitConfig）告诉 Tomcat：

请创建并注册一个 DispatcherServlet

哪些路径交给 DispatcherServlet（比如 / 表示几乎所有请求）

DispatcherServlet 用哪个配置创建 SpringMVC 容器（就是 SpringMvcConfig）

所以它的作用是“接线”：

Tomcat（HTTP 请求） → DispatcherServlet（SpringMVC 总入口） → SpringMVC 容器（里面有 Controller） → Controller 方法

 */


/*
SpringMvcConfig：告诉 Spring “Controller 在哪儿，扫描进容器”（容器内部的配置）

ServletContainersInitConfig：告诉 Tomcat “所有请求先给 DispatcherServlet”，并让它用 SpringMvcConfig 创建容器（容器外部的 Web 启动/接线）
 */