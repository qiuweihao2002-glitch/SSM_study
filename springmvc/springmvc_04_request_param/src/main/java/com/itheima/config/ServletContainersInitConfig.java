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

//    浏览器用 application/x-www-form-urlencoded 提交表单时，如果服务器端没统一设置编码，request.getParameter() 取出来可能是乱码。
//
//    加了这个过滤器后，会在请求进入 Controller 之前做：
//
//            request.setCharacterEncoding("UTF-8")
//
//    这样 @RequestParam、request.getParameter() 等获取到的中文就正常了。
    //例如你用 @RequestParam 或 request.getParameter() 读参数时，编码不统一也会乱码。
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
