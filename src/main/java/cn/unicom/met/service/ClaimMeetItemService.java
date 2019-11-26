package cn.unicom.met.service;

import cn.unicom.met.entity.ClaimMeetItem;
import cn.unicom.met.mapper.ClaimMeetItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimMeetItemService {
    @Autowired
    public ClaimMeetItemMapper claimMeetItemMapper;


    //查询所有
    public List<ClaimMeetItem> getList(int startRecord,int pageSize){
        return claimMeetItemMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return claimMeetItemMapper.getCount();
    }

    //查询所有-分页
    public List<ClaimMeetItem> claimMeetItem_page(int startRecord,int pageSize){
        return claimMeetItemMapper.claimMeetItem_page(startRecord, pageSize);
    }

    //新增
    public void add_claimMeetItem(ClaimMeetItem claimMeetItem){
        claimMeetItemMapper.add_claimMeetItem(claimMeetItem);
    }
    //修改
    public void update_claimMeetItem(ClaimMeetItem claimMeetItem){
        claimMeetItemMapper.update_claimMeetItem(claimMeetItem);
    }
    //删除
    public void del_claimMeetItem(String uuid){
        claimMeetItemMapper.del_claimMeetItem(uuid);
    }
    //查询单个claimMeetItem
    public ClaimMeetItem get_claimMeetItem(String uuid){
        return claimMeetItemMapper.get_claimMeetItem(uuid);
    }


    //搜索查询claimMeetItem
    public List<ClaimMeetItem> find_claimMeetItem(String name,String tele,int startRecord,int pageSize){
        return claimMeetItemMapper.find_claimMeetItem(name,tele,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String name, String tele) {
        return claimMeetItemMapper.findCount(name,tele);
    }
}
