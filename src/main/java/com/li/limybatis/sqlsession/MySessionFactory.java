package com.li.limybatis.sqlsession;

/**
 * @author 李
 * @version 1.0
 * MySessionFactory-会话工厂-返回会话SqlSession
 */
public class MySessionFactory {
    public static MySqlSession openSession() {
        return new MySqlSession();
    }
}
