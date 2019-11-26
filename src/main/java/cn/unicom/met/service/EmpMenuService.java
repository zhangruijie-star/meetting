package cn.unicom.met.service;

import cn.unicom.met.entity.EmpMenu;
import cn.unicom.met.mapper.EmpMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpMenuService {
    @Autowired
    public EmpMenuMapper empMenuMapper;

    public EmpMenu getEmpMenuTree(){
        return empMenuMapper.getEmpMenuTree();
    }

}
