package cn.unicom.met.mapper;

import cn.unicom.met.entity.Dep;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface DepMapper extends BaseMapper<Dep> {
//
//}
public interface DepMapper {
    //查询所有
    public List<Dep> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<Dep> dep_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_dep(@Param("uuid") String uuid);
    //查询单个
    public Dep get_dep(@Param("uuid") String uuid);
    //新增
    public void add_dep(@Param("dep") Dep dep);
    //修改
    public void update_dep(@Param("dep") Dep dep);
    //搜索查询数量
    public int findCount(@Param("name") String name, @Param("tele") String tele);
    //搜索查询dep
    public List<Dep> find_dep(@Param("name") String name, @Param("tele") String tele, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
