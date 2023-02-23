package com.li.entity;

import lombok.*;

import java.util.Date;

/**
 * @author 李
 * @version 1.0
 * Monster类和 monster有映射关系
 * @Getter 给所有属性生成 getter方法
 * @Setter 给所有属性生成 setter方法
 * @ToString 生成toString方法
 * @NoArgsConstructor 生成一个无参构造器
 * @AllArgsConstructor 生成一个全参构造器
 * @Data 会生成上述的所有方法，此外还会生成equals,hashCode等方法
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Monster {
    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private Date birthday;
    private double salary;
    private Integer gender;
}
