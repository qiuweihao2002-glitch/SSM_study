package com.itheima.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //乱码处理
    //1）它主要防的是什么乱码？
    //
    //最常见是这几类：
    //
    //A. 表单提交 / 查询参数（GET/POST 表单）
    //
    //比如：
    //
    //GET /user?name=张三
    //
    //POST 表单 name=张三&...
    //
    //如果服务器用的默认编码不是 UTF-8，就可能读出来变成“????”或一堆乱码。
    //
    //B. 请求体（尤其是 POST 提交）
    //
    //例如你用 @RequestParam 或 request.getParameter() 读参数时，编码不统一也会乱码。
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
