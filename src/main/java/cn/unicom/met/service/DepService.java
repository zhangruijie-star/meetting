package cn.unicom.met.service;

import cn.unicom.met.entity.Dep;
import cn.unicom.met.mapper.DepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService {
    @Autowired
    public DepMapper depMapper;


    //查询所有
    public List<Dep> getList(int startRecord,int pageSize){
        return depMapper.getList(startRecord,pageSize);
    }
    //查询数量
    public int getCount( ){
        return depMapper.getCount();
    }

    //查询所有-分页
    public List<Dep> dep_page(int startRecord,int pageSize){
        return depMapper.dep_page(startRecord, pageSize);
    }

    //新增
    public void add_dep(Dep dep){
        depMapper.add_dep(dep);
    }
    //修改
    public void update_dep(Dep dep){
        depMapper.update_dep(dep);
    }
    //删除
    public void del_dep(String uuid){
        depMapper.del_dep(uuid);
    }
    //查询单个dep
    public Dep get_dep(String uuid){
        return depMapper.get_dep(uuid);
    }


    //搜索查询dep
    public List<Dep> find_dep(String name,String tele,int startRecord,int pageSize){
        return depMapper.find_dep(name,tele,startRecord,pageSize);
    }
    //搜索查询数量
    public int findCount(String name, String tele) {
        return depMapper.findCount(name,tele);
    }
}
