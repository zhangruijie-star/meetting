package cn.unicom.met.mapper;

import cn.unicom.met.entity.ClaimMeet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface ClaimMeetMapper extends BaseMapper<ClaimMeet> {
//
//}
public interface ClaimMeetMapper {
    //查询所有
    public List<ClaimMeet> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<ClaimMeet> claimMeet_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_claimMeet(@Param("uuid") String uuid);
    //查询单个
    public ClaimMeet get_claimMeet(@Param("uuid") String uuid);
    //新增
    public void add_claimMeet(@Param("claimMeet") ClaimMeet claimMeet);
    //修改
    public void update_claimMeet(@Param("claimMeet") ClaimMeet claimMeet);
    //搜索查询数量
    public int findCount(@Param("name") String name, @Param("tele") String tele);
    //搜索查询claimMeet
    public List<ClaimMeet> find_claimMeet(@Param("name") String name, @Param("tele") String tele, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
