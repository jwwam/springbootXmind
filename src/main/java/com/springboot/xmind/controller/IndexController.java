package com.springboot.xmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/xmind")
public class IndexController {

    @RequestMapping(value="/index")
    public String auth(HttpServletRequest request){
        return "/xmind/index";
    }

}
