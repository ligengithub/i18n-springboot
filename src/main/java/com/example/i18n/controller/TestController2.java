package com.example.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ligen
 * @title: TestController2
 * @projectName i18n
 * @description:  实验2 点击按钮切换页面语言
 * @date 2019/12/1120:38
 */
@Controller
public class TestController2 {

    /**
     *@ desc : 这里有两种方法
     *     1 我们在这里设置SessionLocaleResolver的区域信息，下面1-1 ，，1-2 两种方法均可，区域解析器对应（配置类中的方法1）
     *
     *     2 这里不处理，直接return  ，我们在Config配置类中，配置自定义区域解析器（对应配置类中的方法2）。
     *@ date 2019/12/12
     */
    @GetMapping(value = "index")
    public String toIndex(HttpServletRequest request,HttpServletResponse response,String lang){

         // 点击连接跳转 方法1-1  setAttribute的方式去设置 SessionLocaleResolver 的LOCALE_SESSION_ATTRIBUTE_NAME 属性
         //       if ("zh_CN".equals(lang)){
         //           request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("zh","CN"));
         //       }else if("en_US".equals(lang)){
         //           request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en","US"));
         //       }

         //  点击连接跳转 方法1-2  直接从参数取得local信息，构造一个local对象，然后设置SessionLocaleResolver的local属性，
         //  和上面大同小异
         //        SessionLocaleResolver slr = new SessionLocaleResolver();
         //        String[] split = lang.split("_");
         //        Locale locale = new Locale(split[0], split[1]);
         //        slr.setLocale(request, response, locale);

          // 方法2 这里什么都不处理，在配置类中配置区域解析器，为我们自定义的区域解析器
        return "index";
    }

}
