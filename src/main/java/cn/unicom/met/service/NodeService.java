package cn.unicom.met.service;

import cn.unicom.met.entity.DeptEmtity;
import cn.unicom.met.mapper.NodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {
    @Autowired
    public NodeMapper nodeMapper;

    public List<DeptEmtity> getNodeTree(){
        return nodeMapper.getNodeTree();
    }

}
