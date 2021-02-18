package com.home.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 角色实体类
 *
 * @author 冯根源
 * @date 2021/1/24 14:03
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}