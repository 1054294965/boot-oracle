package com.wzh.demo.controller;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Son extends Father{
    private Life life;

    public static void main(String[] args) {
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(cuishan,wuji);


//        Life wujiLife = wuji.getLife();
//        wujiLife.setStatus("alive");
//        wuji.setLife(wujiLife);
//        cuishanLife.setStatus("dead"); // 翠山后来自刎了

//        cuishanLife.setStatus("dead"); // 翠山后来自刎了
//        Life wujiLife = wuji.getLife();
//        wujiLife.setStatus("alive");
//        wuji.setLife(wujiLife);



        cuishanLife.setStatus("dead"); // 翠山自刎了  该行放在上下均可
        // 无忌用个新对象 不受翠山影响了
        Life wujiLife = new Life();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);



        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }
}
