package com.li.limybatis.sqlsession;

/**
 * @author 李
 * @version 1.0
 * MySqlSession：搭建Configuration(连接)和Executor之间的桥梁
 */
public class MySqlSession {
    //执行器
    private Executor executor = new MyExecutor();
    //配置
    private MyConfiguration myConfiguration = new MyConfiguration();

    //编写方法selectOne，返回一条记录
    public <T> T selectOne(String statement,Object parameter){
        return executor.query(statement, parameter);
    }
}
