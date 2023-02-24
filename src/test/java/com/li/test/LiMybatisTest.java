package com.li.test;

import com.li.entity.Monster;
import com.li.limybatis.config.MapperBean;
import com.li.limybatis.sqlsession.*;
import com.li.mapper.MonsterMapper;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 李
 * @version 1.0
 */
public class LiMybatisTest {
    @Test
    public void build() {
        MyConfiguration myConfiguration = new MyConfiguration();
        Connection connection = myConfiguration.build("my-config.xml");
        System.out.println("connection=--" + connection);
    }

    @Test
    public void query() {
        Executor executor = new MyExecutor();
        Monster monster =
                (Monster) executor.query("select * from monster where id = ?", 1);
        System.out.println("monster--" + monster);
    }

    @Test
    public void selectOne() {
        MySqlSession mySqlSession = new MySqlSession();
        Monster monster =
                (Monster) mySqlSession.selectOne("select * from monster where id=?", 1);
        System.out.println("monster=" + monster);
    }

    @Test
    public void readMapper() {
        MyConfiguration myConfiguration = new MyConfiguration();
        MapperBean mapperBean = myConfiguration.readMapper("MonsterMapper.xml");
        System.out.println("mapperBean=" + mapperBean);
    }

    @Test
    public void getMapper() {
        MySqlSession mySqlSession = new MySqlSession();
        //mapper为代理类型
        MonsterMapper mapper = mySqlSession.getMapper(MonsterMapper.class);
        System.out.println("mapper的运行类型=" + mapper.getClass());//class com.sun.proxy.$Proxy4
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster--" + monster);
    }

    @Test
    public void openSession() {
        MySqlSession mySqlSession = MySessionFactory.openSession();
        MonsterMapper mapper = mySqlSession.getMapper(MonsterMapper.class);
        System.out.println("mapper的运行类型=" + mapper.getClass());
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster--" + monster);
    }

}
