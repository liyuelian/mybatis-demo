package com.li.limybatis.sqlsession;

/**
 * @author 李
 * @version 1.0
 */
public interface Executor {
    //泛型方法
    public <T> T query(String statement, Object parameter);
}
