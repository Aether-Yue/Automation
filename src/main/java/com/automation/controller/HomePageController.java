package com.automation.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class HomePageController {
    //to do by Aether.yue
    @RequestMapping("/")
    public String index(Model model) {
        String name = "Hello Grea";
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/execute")
    /**
     * @Description: View跳转，跳转到Execute页面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Aether.Yue
     * @Date: 2019/2/28
     */
    public String execute(Model model) {
        return "execute";
    }


}
