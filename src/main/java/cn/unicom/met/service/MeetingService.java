package cn.unicom.met.service;

import cn.unicom.met.entity.Meeting;
import cn.unicom.met.mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {
    @Autowired
    public MeetingMapper meetingMapper;


    //查询所有
    public List<Meeting> getList(int startRecord,int pageSize){
        return meetingMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return meetingMapper.getCount();
    }

    //查询所有-分页
    public List<Meeting> meeting_page(int startRecord,int pageSize){
        return meetingMapper.meeting_page(startRecord, pageSize);
    }

    //新增
    public void add_meeting(Meeting meeting){
        meetingMapper.add_meeting(meeting);
    }
    //修改
    public void update_meeting(Meeting meeting){
        meetingMapper.update_meeting(meeting);
    }
    //删除
    public void del_meeting(String uuid){
        meetingMapper.del_meeting(uuid);
    }
    //查询单个meeting
    public Meeting get_meeting(String uuid){
        return meetingMapper.get_meeting(uuid);
    }


    //搜索查询meeting
    public List<Meeting> find_meeting(String login_uuid,String mtitle,String mtype,int startRecord,int pageSize){
        return meetingMapper.find_meeting(login_uuid,mtitle,mtype,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String login_uuid,String mtitle, String mtype) {
        return meetingMapper.findCount(login_uuid,mtitle,mtype);
    }
    //审核人
    //搜索查询meeting
    public List<Meeting> findNextMeeting(String login_uuid,String mtitle,String mtype,int startRecord,int pageSize){
        return meetingMapper.findNextMeeting(login_uuid,mtitle,mtype,startRecord,pageSize);
    }
    //搜索查询数量
    public int findNextCount(String login_uuid,String mtitle, String mtype) {
        return meetingMapper.findNextCount(login_uuid,mtitle,mtype);
    }
}
