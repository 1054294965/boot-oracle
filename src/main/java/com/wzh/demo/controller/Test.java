package com.wzh.demo.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.ArrayList;
public class Test {

    private static Logger log = LoggerFactory.getLogger(Test.class
    );
    private String productName;
    private Double amount;
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }




    public static void main(String[] args)  {

        Double d1=new Double(1.0001);
        Double d2=new Double(1.0001);
        double d3=d2-d1;
        System.out.println(Double.compare(d1,d2));


    }
}
