package cn.unicom.met.service;

import cn.unicom.met.entity.ClaimMeet;
import cn.unicom.met.mapper.ClaimMeetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimMeetService {
    @Autowired
    public ClaimMeetMapper claimMeetMapper;


    //查询所有
    public List<ClaimMeet> getList(int startRecord,int pageSize){
        return claimMeetMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return claimMeetMapper.getCount();
    }

    //查询所有-分页
    public List<ClaimMeet> claimMeet_page(int startRecord,int pageSize){
        return claimMeetMapper.claimMeet_page(startRecord, pageSize);
    }

    //新增
    public void add_claimMeet(ClaimMeet claimMeet){
        claimMeetMapper.add_claimMeet(claimMeet);
    }
    //修改
    public void update_claimMeet(ClaimMeet claimMeet){
        claimMeetMapper.update_claimMeet(claimMeet);
    }
    //删除
    public void del_claimMeet(String uuid){
        claimMeetMapper.del_claimMeet(uuid);
    }
    //查询单个claimMeet
    public ClaimMeet get_claimMeet(String uuid){
        return claimMeetMapper.get_claimMeet(uuid);
    }


    //搜索查询claimMeet
    public List<ClaimMeet> find_claimMeet(String name,String tele,int startRecord,int pageSize){
        return claimMeetMapper.find_claimMeet(name,tele,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String name, String tele) {
        return claimMeetMapper.findCount(name,tele);
    }
}
