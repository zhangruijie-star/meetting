package cn.unicom.met.mapper;

import cn.unicom.met.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface DepMapper extends BaseMapper<Dep> {
//
//}
public interface MenuMapper {
    //查询所有
    Menu getMenuTree();

}
