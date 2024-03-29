package com.home.mapper;

import com.home.entity.Role;
import com.home.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByKeyword(String keyword);

    /**
     * 查询被分配的角色
     * @param adminId
     * @return
     */
    List<Role> selectAssignedRole(Integer adminId);

    /**
     * 查询没有被分配的角色
     * @param adminId
     * @return
     */
    List<Role> selectUnAssignedRole(Integer adminId);
}
