package com.li.mapper;

import com.li.entity.Monster;

/**
 * @author 李
 * @version 1.0
 * MonsterMapper：声明对数据库的crud方法
 */
public interface MonsterMapper {
    //查询方法
    public Monster getMonsterById(Integer id);

}
