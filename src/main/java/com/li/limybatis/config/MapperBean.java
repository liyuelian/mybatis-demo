package com.li.limybatis.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 李
 * @version 1.0
 * MapperBean：将我们的Mapper信息，进行封装
 */
@Setter
@Getter
public class MapperBean {
    private String interfaceName;//接口名
    //接口下的所有方法
    public List<Function> functions;
}
