package com.automation.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    private static Logger logger = Logger.getLogger(String.valueOf(TestController.class));
    private static Boolean isChanel;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String test(@RequestParam(value = "project", required = true) String project_name, @RequestBody String body) {
        String result_json = "[{\"test002_onlyone\":\"FAILED\"}，{\"test001_onlyone\":\"SUCCESS\"}]";
        return result_json;
    }
}

