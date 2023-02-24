package com.li.limybatis.sqlsession;

import com.li.limybatis.config.Function;
import com.li.limybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 李
 * @version 1.0
 * MyMapperProxy：动态代理生成 Mapper对象，调用 MyExecutor方法
 */
public class MyMapperProxy implements InvocationHandler {
    private MySqlSession mySqlSession;
    private String mapperFile;
    private MyConfiguration myConfiguration;

    //构造器
    public MyMapperProxy(MySqlSession mySqlSession, MyConfiguration myConfiguration, Class clazz) {
        this.mySqlSession = mySqlSession;
        this.myConfiguration = myConfiguration;
        this.mapperFile = clazz.getSimpleName() + ".xml";
    }

    //当执行Mapper接口的代理对象方法时，会执行到invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = myConfiguration.readMapper(this.mapperFile);
        //判断是否是xml文件对应的接口
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            //通过method拿到执行的方法所在的接口的名称，与MapperBean存放的接口名比较
            return null;
        }
        //取出MapperBean的functions
        List<Function> functions = mapperBean.getFunctions();
        //判断当前mapperBean解析对应的XML文件后，有方法
        if (null != functions && 0 != functions.size()) {
            for (Function function : functions) {
                //如果当前要执行的方法和function.getFuncName()一样
                //说明我们可以从当前遍历的function对象中，取出相应的信息sql，并执行方法
                if (method.getName().equals(function.getFuncName())) {
                    //如果当前function要执行的SqlType是select，就去执行selectOne
                    /*
                     * 说明：
                     * 1.如果要执行的方法是select，就对应执行selectOne
                     *   因为我们在MySqlSession只写了一个方法（selectOne）
                     * 2.实际上原生的MySqlSession中应该有很多的方法，只是这里简化了,
                     *    实际上应该根据不同的匹配情况调用不同的方法，并且还需要进行参数解析处理，
                     *    还有比较复杂的字符串处理，拼接sql，处理返回类型等工作
                     * 3.因为这里主要想实现mybatis生成mapper动态代理对象，调用方法的机制，所以简化
                     */
                    if ("select".equalsIgnoreCase(function.getSqlType())) {
                        return mySqlSession
                                .selectOne(function.getSql(), String.valueOf(args[0]));
                    }
                }
            }
        }
        return null;
    }
}
