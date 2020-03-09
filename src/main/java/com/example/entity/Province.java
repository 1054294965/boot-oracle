package com.example.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;
@Data
@Alias("Province")
public class Province {
    private String provName;
    private String bb;
    private String by1;
    private String by2;
    private Date drsj;
    private Date gxsj;
    private String czr;
}
