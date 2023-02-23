package com.li.limybatis.sqlsession;

import com.li.entity.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 李
 * @version 1.0
 */
public class MyExecutor implements Executor {
    private MyConfiguration myConfiguration = new MyConfiguration();

    /**
     * 根据sql，返回查询结果
     *
     * @param sql
     * @param parameter
     * @param <T>
     * @return
     */
    @Override
    public <T> T query(String sql, Object parameter) {
        //获取连接对象
        Connection connection = getConnection();
        //查询返回的结果集
        ResultSet set = null;
        PreparedStatement pre = null;
        try {
            //构建PreparedStatement对象
            pre = connection.prepareStatement(sql);
            //设置参数，如果参数多，可以使用数组处理
            pre.setString(1, parameter.toString());
            //查询返回的结果集
            set = pre.executeQuery();
            //把结果集的数据封装到对象中-monster
            //说明：这里做了简化处理，认为返回的结果就是一个monster记录，完善的写法应该使用反射机制
            Monster monster = new Monster();
            //遍历结果集,将数据封装到monster对象中
            while (set.next()) {
                monster.setId(set.getInt("id"));
                monster.setName(set.getString("name"));
                monster.setEmail(set.getString("email"));
                monster.setAge(set.getInt("age"));
                monster.setGender(set.getInt("gender"));
                monster.setBirthday(set.getDate("birthday"));
                monster.setSalary(set.getDouble("salary"));
            }
            return (T) monster;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //编写方法，通过myConfiguration对象返回连接
    private Connection getConnection() {
        Connection connection = myConfiguration.build("my-config.xml");
        return connection;
    }
}
