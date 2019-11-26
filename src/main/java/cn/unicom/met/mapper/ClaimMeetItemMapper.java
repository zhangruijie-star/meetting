package cn.unicom.met.mapper;

import cn.unicom.met.entity.ClaimMeetItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface ClaimMeetItemMapper extends BaseMapper<ClaimMeetItem> {
//
//}
public interface ClaimMeetItemMapper {
    //查询所有
    public List<ClaimMeetItem> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<ClaimMeetItem> claimMeetItem_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_claimMeetItem(@Param("uuid") String uuid);
    //查询单个
    public ClaimMeetItem get_claimMeetItem(@Param("uuid") String uuid);
    //新增
    public void add_claimMeetItem(@Param("claimMeetItem") ClaimMeetItem claimMeetItem);
    //修改
    public void update_claimMeetItem(@Param("claimMeetItem") ClaimMeetItem claimMeetItem);
    //搜索查询数量
    public int findCount(@Param("name") String name, @Param("tele") String tele);
    //搜索查询claimMeetItem
    public List<ClaimMeetItem> find_claimMeetItem(@Param("name") String name, @Param("tele") String tele, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
