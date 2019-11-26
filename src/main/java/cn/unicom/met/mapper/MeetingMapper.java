package cn.unicom.met.mapper;

import cn.unicom.met.entity.Meeting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeetingMapper {
    //查询所有
    public List<Meeting> getList(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //查询数量
    public int getCount();
    //条件查询-page/rows
    public List<Meeting> meeting_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
    public void del_meeting(@Param("uuid") String uuid);
    //查询单个
    public Meeting get_meeting(@Param("uuid") String uuid);
    //新增
    public void add_meeting(@Param("meeting") Meeting meeting);
    //修改
    public void update_meeting(@Param("meeting") Meeting meeting);
    //搜索查询数量
    public int findCount(@Param("login_uuid") String login_uuid, @Param("mtitle") String name, @Param("mtype") String mtype);
    //搜索查询meeting
    public List<Meeting> find_meeting(@Param("login_uuid") String login_uuid,@Param("mtitle") String mtitle, @Param("mtype") String mtype, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);

    //审核人
    public int findNextCount(@Param("login_uuid") String login_uuid, @Param("mtitle") String name, @Param("mtype") String mtype);
    //搜索查询meeting
    public List<Meeting> findNextMeeting(@Param("login_uuid") String login_uuid,@Param("mtitle") String mtitle, @Param("mtype") String mtype, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);

}
