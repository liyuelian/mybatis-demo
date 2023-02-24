package com.li.limybatis.sqlsession;

import java.lang.reflect.Proxy;

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
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    /**
     * 1.回 mapper的动态代理对象
     * 2.这里的 clazz到时传入的类似 MonsterMapper.class
     * 3.返回的就是 MonsterMapper 接口的代理对象
     * 4.当执行接口方法时（通过代理对象调用），
     *   根据动态代理机制会执行到MyMapperProxy的invoke()方法
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        //返回动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new MyMapperProxy(this, myConfiguration, clazz));
    }
}
