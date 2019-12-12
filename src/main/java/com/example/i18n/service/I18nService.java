package com.example.i18n.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author ligen
 * @title: I18nService
 * @projectName i18n
 * @description:
 * @date 2019/10/2316:12
 */
@Service
public class I18nService {
    /**
     *@ desc : i18n的Service  ，通过该Service的方法去配置文件中。 这是一种方法。
     *@ params
     *@ return
     *@ date 2019/10/23
     */
    @Autowired
    private MessageSource messageSource;

    public I18nService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String msgKey) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(msgKey, null, locale);
        System.out.println(msg);
        return msg;
    }
}
