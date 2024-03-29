package com.home.mapper;

import com.home.entity.Auth;
import com.home.entity.AuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    /**
     * 查询角色被赋予的权限
     * @param roleId
     * @return
     */
    List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 根据角色ID删除关联表中的权限
     * @param roleId
     */
    void deleteOldRelationship(Integer roleId);

    /**
     * 根据角色Id添加关联表中的权限
     * @param roleId
     * @param authIdList
     */
    void insertNewRelationship(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);

    /**
     * 根据用户ID查询角色信息
     * @param adminId
     * @return
     */
    List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}
