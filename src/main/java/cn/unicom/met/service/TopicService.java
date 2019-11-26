package cn.unicom.met.service;

import cn.unicom.met.entity.Topic;
import cn.unicom.met.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    public TopicMapper topicMapper;


    //查询所有
    public List<Topic> getList(String met_uuid){
        return topicMapper.getList(met_uuid);
    }
//    //查询数量
//    public int getCount( ){
//        return topicMapper.getCount();
//    }
//
//    //查询所有-分页
//    public List<Topic> topic_page(int startRecord,int pageSize){
//        return topicMapper.topic_page(startRecord, pageSize);
//    }

    //新增
    public void add_topic(Topic topic){
        topicMapper.add_topic(topic);
    }
//    //修改
//    public void update_topic(Topic topic){
//        topicMapper.update_topic(topic);
//    }
//    //删除
//    public void del_topic(String uuid){
//        topicMapper.del_topic(uuid);
//    }
//    //查询单个topic
//    public Topic get_topic(String uuid){
//        return topicMapper.get_topic(uuid);
//    }
//
//
//    //搜索查询topic
//    public List<Topic> find_topic(String name,String meetinguuid,int startRecord,int pageSize){
//        return topicMapper.find_topic(name,meetinguuid,startRecord,pageSize);
//    }
//    //搜索查询数量
//    public int findCount(String name, String meetinguuid) {
//        return topicMapper.findCount(name,meetinguuid);
//    }
}
