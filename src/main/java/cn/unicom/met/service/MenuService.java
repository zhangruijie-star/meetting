package cn.unicom.met.service;

import cn.unicom.met.entity.Menu;
import cn.unicom.met.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    public MenuMapper menuMapper;

    public Menu getMenuTree(){
        return menuMapper.getMenuTree();
    }

}
