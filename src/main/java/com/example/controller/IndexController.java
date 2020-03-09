package com.example.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Watchable;
import java.util.Random;

@Controller
class IndexController {
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","hello pillar");
        return "index";
    }


}
