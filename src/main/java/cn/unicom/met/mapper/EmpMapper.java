package cn.unicom.met.mapper;

import cn.unicom.met.entity.Emp;
import cn.unicom.met.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface EmpMapper extends BaseMapper<Emp> {
//
//}
public interface EmpMapper {
    //查询所有
    public List<Emp> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<Emp> emp_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_emp(@Param("uuid") String uuid);
    //查询单个
    public Emp get_emp(@Param("uuid") String uuid);
    //新增
    public void add_emp(@Param("emp") Emp emp);
    //修改
    public void update_emp(@Param("emp") Emp emp);
    //搜索查询数量
    public int findCount(@Param("name") String name, @Param("tele") String tele);
    //搜索查询emp
    public List<Emp> find_emp(@Param("name") String name, @Param("tele") String tele, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //验证登陆emp
    public Emp login_emp(@Param("uuid")String uuid , @Param("pwd") String pwd);
    //查询用户下的菜单权限
    public List<Menu> getMenusByEmpuuid(@Param("uuid")String uuid);
    //对菜单进行补全，克隆
//    public Menu readMenusByEmpuuid(@Param("uuid")String uuid);
}
