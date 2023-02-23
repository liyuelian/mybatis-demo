package com.li.test;

import com.li.entity.Monster;
import com.li.limybatis.sqlsession.Executor;
import com.li.limybatis.sqlsession.MyConfiguration;
import com.li.limybatis.sqlsession.MyExecutor;
import com.li.limybatis.sqlsession.MySqlSession;
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
}
