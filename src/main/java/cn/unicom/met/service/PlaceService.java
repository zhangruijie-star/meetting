package cn.unicom.met.service;

import cn.unicom.met.entity.Place;
import cn.unicom.met.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    public PlaceMapper placeMapper;


    //查询所有
    public List<Place> getList(int startRecord,int pageSize){
        return placeMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return placeMapper.getCount();
    }

    //查询所有-分页
    public List<Place> place_page(int startRecord,int pageSize){
        return placeMapper.place_page(startRecord, pageSize);
    }

    //新增
    public void add_place(Place place){
        placeMapper.add_place(place);
    }
    //修改
    public void update_place(Place place){
        placeMapper.update_place(place);
    }
    //删除
    public void del_place(String uuid){
        placeMapper.del_place(uuid);
    }
    //查询单个place
    public Place get_place(String uuid){
        return placeMapper.get_place(uuid);
    }


    //搜索查询place
    public List<Place> find_place(String address,int startRecord,int pageSize){
        return placeMapper.find_place(address,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String address) {
        return placeMapper.findCount(address);
    }
}
