package cn.unicom.met.service;

import cn.unicom.met.entity.Mettype;
import cn.unicom.met.mapper.MettypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MettypeService {
    @Autowired
    public MettypeMapper mettypeMapper;


    //查询所有
    public List<Mettype> getList(int startRecord,int pageSize){
        return mettypeMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return mettypeMapper.getCount();
    }

    //查询所有-分页
    public List<Mettype> mettype_page(int startRecord,int pageSize){
        return mettypeMapper.mettype_page(startRecord, pageSize);
    }

    //新增
    public void add_mettype(Mettype mettype){
        mettypeMapper.add_mettype(mettype);
    }
    //修改
    public void update_mettype(Mettype mettype){
        mettypeMapper.update_mettype(mettype);
    }
    //删除
    public void del_mettype(String uuid){
        mettypeMapper.del_mettype(uuid);
    }
    //查询单个mettype
    public Mettype get_mettype(String uuid){
        return mettypeMapper.get_mettype(uuid);
    }


    //搜索查询mettype
    public List<Mettype> find_mettype(String mtype,int startRecord,int pageSize){
        return mettypeMapper.find_mettype(mtype,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String mtype) {
        return mettypeMapper.findCount(mtype);
    }
}
