package com.example.dao;

import com.example.entity.Province;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public interface ProvinceMapper {
//    public void create(Province province);
    public List<Province> read();
//    public void update(Province province);
//    public void delete(String id);

}
