package cn.unicom.met.mapper;

import cn.unicom.met.entity.DeptEmtity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface DepMapper extends BaseMapper<Dep> {
//
//}
public interface NodeMapper {
    //查询所有
    List<DeptEmtity> getNodeTree();

}
