package cn.unicom.met.mapper;

import cn.unicom.met.entity.Mettype;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface MettypeMapper extends BaseMapper<Mettype> {
//
//}
public interface MettypeMapper {
    //查询所有
    public List<Mettype> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<Mettype> mettype_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_mettype(@Param("uuid") String uuid);
    //查询单个
    public Mettype get_mettype(@Param("uuid") String uuid);
    //新增
    public void add_mettype(@Param("mettype") Mettype mettype);
    //修改
    public void update_mettype(@Param("mettype") Mettype mettype);
    //搜索查询数量
    public int findCount(@Param("mtype") String mtype);
    //搜索查询mettype
    public List<Mettype> find_mettype(@Param("mtype") String mtype, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
