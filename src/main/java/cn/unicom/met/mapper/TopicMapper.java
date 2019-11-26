package cn.unicom.met.mapper;

import cn.unicom.met.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//public interface TopicMapper extends BaseMapper<Topic> {
//
//}
public interface TopicMapper {
    //查询所有
    public List<Topic> getList(@Param("met_uuid")String met_uuid);
    //查询数量
//    public int getCount();
    //条件查询-page/rows
//    public List<Topic> topic_page(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
    //删除
//    public void del_topic(@Param("uuid") String uuid);
    //查询单个
//    public Topic get_topic(@Param("uuid") String uuid);
    //新增
    public void add_topic(@Param("topic") Topic topic);
    //修改
//    public void update_topic(@Param("topic") Topic topic);
    //搜索查询数量
//    public int findCount(@Param("name") String name, @Param("meetinguuid") String meetinguuid);
    //搜索查询topic
//    public List<Topic> find_topic(@Param("name") String name, @Param("meetinguuid") String meetinguuid, @Param("startRecord") int startRecord, @Param("pageSize") int pageSize);
}
