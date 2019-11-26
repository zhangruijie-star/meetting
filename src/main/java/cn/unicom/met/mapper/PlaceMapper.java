package cn.unicom.met.mapper;

import cn.unicom.met.entity.Place;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface PlaceMapper extends BaseMapper<Place> {
//
//}
public interface PlaceMapper {
    //查询所有
    public List<Place> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<Place> place_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_place(@Param("uuid") String uuid);
    //查询单个
    public Place get_place(@Param("uuid") String uuid);
    //新增
    public void add_place(@Param("place") Place place);
    //修改
    public void update_place(@Param("place") Place place);
    //搜索查询数量
    public int findCount(@Param("address") String address );
    //搜索查询place
    public List<Place> find_place(@Param("address") String address, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
