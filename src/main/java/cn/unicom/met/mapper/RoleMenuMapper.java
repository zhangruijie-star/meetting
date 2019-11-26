package cn.unicom.met.mapper;

import cn.unicom.met.entity.Role;
import cn.unicom.met.entity.RoleMenu;
import cn.unicom.met.entity.Tree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMenuMapper {
    //查询角色对应的权限
    List<RoleMenu> getRoleMenu(@Param("roleid") String roleid);
    //查询角色列表
    List<Role> getRoleList();
    //删除某个角色id对应的权限
    void deleteRole(@Param("roleid") String roleid);
    //
    void addrolemenu(@Param("roleid") String roleid,@Param("menuid") String menuid);

}
