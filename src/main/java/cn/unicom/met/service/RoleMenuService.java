package cn.unicom.met.service;

import cn.unicom.met.entity.DeptEmtity;
import cn.unicom.met.entity.Role;
import cn.unicom.met.entity.RoleMenu;
import cn.unicom.met.entity.Tree;
import cn.unicom.met.mapper.NodeMapper;
import cn.unicom.met.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService {
    @Autowired
    public RoleMenuMapper  roleMenuMapper;

    //查询角色对应的权限
    public List<RoleMenu> getRoleMenu(String roleid){
        return roleMenuMapper.getRoleMenu(roleid);
    }
    //查询角色列表
    public List<Role> getRoleList(){
        return roleMenuMapper.getRoleList();
    }
    //删除某个角色id对应的权限
    public void deleteRole(String roleid){
        roleMenuMapper.deleteRole(roleid);
    }
    //新增某个角色id对应的权限
    public void addrolemenu(String roleid,String Menuid){
        roleMenuMapper.addrolemenu(roleid,Menuid);
    }
}
