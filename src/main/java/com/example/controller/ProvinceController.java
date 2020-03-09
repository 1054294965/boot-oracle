package com.example.controller;

import com.example.dao.ProvinceMapper;
import com.example.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("province")
public class ProvinceController {
    @Autowired
    private ProvinceMapper provinceMapper;
    @ResponseBody
    @RequestMapping("/list")
    public List<Province> list(){
        Province province = new Province();
        List<Province> list = provinceMapper.read();
        return list;
    }
}
