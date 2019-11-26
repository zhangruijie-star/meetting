package cn.unicom.met.mapper;

import cn.unicom.met.entity.EmpMenu;
import cn.unicom.met.entity.Menu;
import org.springframework.stereotype.Component;

@Component
//public interface DepMapper extends BaseMapper<Dep> {
//
//}
public interface EmpMenuMapper {
    //查询所有
    EmpMenu getEmpMenuTree();

}
