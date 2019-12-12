package com.example.i18n.controller;

import com.example.i18n.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ligen
 * @title: TestController
 * @projectName i18n
 * @description:   入门实验1
 *                设置浏览器语言为English 调用接口返回  "name"
 *                设置为中文......................返回  "名字"
 * @date 2019/10/2315:57
 */
@RestController
public class TestController {

    @Autowired
    I18nService i18nService;

    @GetMapping("/get")
    public String test_i18n() {
        return i18nService.getMessage("name");
    }

}
