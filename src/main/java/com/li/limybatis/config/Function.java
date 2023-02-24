package com.li.limybatis.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李
 * @version 1.0
 * Function：记录对应 Mapper.xml的方法信息
 */
@Getter
@Setter
@ToString
public class Function {
    private String sqlType;//sql类型，如select，update，insert，delete
    private String funcName;//方法名
    private String sql;//执行的sql语句
    private Object resultType;//返回类型
    private String parameterType;//参数类型
}
