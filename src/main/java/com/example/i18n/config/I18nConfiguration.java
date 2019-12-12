package com.example.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * @author ligen
 * @title: I18nConfiguration
 * @projectName i18n
 * @description:
 * @date 2019/10/2316:10
 */
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

    /**
     *@ desc :    国际化本质上，我们需要的仅仅时两个东西
     *          1 区域解析器，，根据客户端的请求解析出客户端的区域
     *          2 国际化文件配置
     *
     *          - 两个都有Springbooe都有默认的配置，如果你不需要通过页面点击来切换国际化。
     *          那么你不需要去配置区域解析器localeResolver
     *          - 如果你国际化配置文件也使用默认的message_en.properties, message_zh.properties
     *          那么你 messageSource也不用配置(配置项主要是国际化配置文件的位置，可以使用注入Bean的方式，
     *          也可以直接在配置文件中配置)
     *@ params
     *@ return
     *@ date 2019/12/12
     */


    /**
     * Accept-Language Header 区域解析器  如果不需要修改默认配置的话也不用配置
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
//        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
//        return acceptHeaderLocaleResolver;
//    }



/**
 *@ desc : 点击连接跳转 方法1  通过SessionLocaleResolver,
 *            需要再Controller中设置Session的属性值
 *@ params
 *@ return
 *@ date 2019/12/12
 */
//    @Bean(name = "localeResolver")
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return slr;
//    }

    /**
     * @ desc : 点击连接跳转 方法2 用自己定义的区域解析器
     * @ params
     * @ return
     * @ date 2019/12/12
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        return new MylocalResolver();
    }


    /**
     * 加载国际化配置文件资源，，可以使用配置文件中说明，也可以以注入Bean的方式配置
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //设置国际化配置文件存放目录
        messageSource.setBasename("classpath:i18n/test");
        //设置加载资源的缓存失效时间，-1表示永久有效，默认为-1
        messageSource.setCacheSeconds(-1);
        //设定 Resource Bundle 编码方式，默认为UTF-8
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
    }


    /**
     *@ desc :  定义了一个内部类 ，自定义区域解析器(如果使用方法1（SessionLocaleResolver）的话则不需要，因为在Controller 中已经将区域信息解析完成了)
     *        这个区域解析器，从requestParam 上获取信息
     *@ params
     *@ return
     *@ date 2019/12/12
     */
    public  class MylocalResolver implements LocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest httpServletRequest) {
            String l = httpServletRequest.getParameter("lang");
//            Locale locale = Locale.getDefault();     //如果超链接上没带区域信息参数就用系统默认的
            Locale locale = Locale.SIMPLIFIED_CHINESE; // 设置默认区域，可以获取默认，也可以自己设置默认
            if (!StringUtils.isEmpty(l)) {
                String[] split = l.split("_");
                locale = new Locale(split[0], split[1]);//parameter1：语言代码参数，parameter2：国家代码参数
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

        }
    }

}
