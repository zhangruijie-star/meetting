package cn.unicom.met.service;

import cn.unicom.met.entity.Emp;
import cn.unicom.met.entity.Menu;
import cn.unicom.met.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    public EmpMapper empMapper;


    //查询所有
    public List<Emp> getList(int startRecord,int pageSize){
        return empMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return empMapper.getCount();
    }

    //查询所有-分页
    public List<Emp> emp_page(int startRecord,int pageSize){
        return empMapper.emp_page(startRecord, pageSize);
    }

    //新增
    public void add_emp(Emp emp){
        empMapper.add_emp(emp);
    }
    //修改
    public void update_emp(Emp emp){
        empMapper.update_emp(emp);
    }
    //删除
    public void del_emp(String uuid){
        empMapper.del_emp(uuid);
    }
    //查询单个emp
    public Emp get_emp(String uuid){
        return empMapper.get_emp(uuid);
    }


    //搜索查询emp
    public List<Emp> find_emp(String name,String tele,int startRecord,int pageSize){
        return empMapper.find_emp(name,tele,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String name, String tele) {
        return empMapper.findCount(name,tele);
    }

    //验证登陆emp
    public Emp login_emp(String uuid,String pwd){
        return empMapper.login_emp(uuid,pwd);
    }

    //查询用户下的菜单权限
    public List<Menu> getMenusByEmpuuid(String uuid){
        return empMapper.getMenusByEmpuuid(uuid);
    }
//    //对菜单进行补全，克隆
//    public Menu readMenusByEmpuuid(String uuid){
//        return empMapper.readMenusByEmpuuid(uuid);
//    }
}
