package com.li.limybatis.sqlsession;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 李
 * @version 1.0
 * 用来读取xml文件，建立连接
 */
public class MyConfiguration {
    //属性-类的加载器
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    //读取xml文件并处理
    public Connection build(String resource) {
        Connection connection = null;
        try {
            //先加载配置文件 my-config.xml，获取对应的InputStream
            InputStream stream = loader.getResourceAsStream(resource);
            //解析 my-config.xml文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            //获取 xml文件的根元素 <database>
            Element root = document.getRootElement();
            System.out.println("root=" + root);
            //根据root解析，获取Connection
            connection = evalDataSource(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //解析 my-config.xml 的信息，并返回 Connection
    private Connection evalDataSource(Element node) {
        if (!"database".equals(node.getName())) {
            throw new RuntimeException("root节点应该是<database>");
        }

        //连接DB的必要参数
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        //遍历node下的子节点，获取其属性值
        for (Object item : node.elements("property")) {
            //i就是对应的 property节点
            Element i = (Element) item;
            //property节点的 name属性的值
            String name = i.attributeValue("name");
            //property节点的 value属性的值
            String value = i.attributeValue("value");

            //判断值是否为空
            if (name == null || value == null) {
                throw new RuntimeException("property节点没有设置name或value属性！");
            }
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("属性名没有匹配到..");
            }
        }
        //获取连接
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
