package cn.unicom.met.controller;

import cn.unicom.met.entity.DeptEmtity;
import cn.unicom.met.entity.Emp;
import cn.unicom.met.entity.Menu;
import cn.unicom.met.service.EmpService;
import cn.unicom.met.service.MenuService;
import cn.unicom.met.service.NodeService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//GetMapping 与 PostMapping的区别

/*** GetMapping
 *         //提取url中的page和rows参数值
 * //        int page=Integer.parseInt(request.getParameter("page"));
 * //        int pageSzie=Integer.parseInt(request.getParameter("rows"));
 * **PostMapping
 * public Map<String,Object> depByPage(HttpServletRequest request,
 *                                         @RequestParam("page") String page_str,
 *                                         @RequestParam("rows") String pageSziestr)
 */
@Controller
public class MenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private MenuService menuService;

    @Autowired
    private EmpService empService;
    // 查询单个部门，属性驱动，条件查询
    @GetMapping(value = "/getMenuTree",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getMenu(HttpSession session) {
        //获取登陆用户
        Emp loginEmp = (Emp) session.getAttribute("login_emp");
        String uuid=loginEmp.getUuid();
        //获取用户的权限菜单
        List<Menu> menuList=empService.getMenusByEmpuuid(uuid);
        String login_menuString= JSON.toJSONString(menuList);
        System.out.println("login_menuString..."+login_menuString);
        //原来的所有菜单
        Menu menus= (Menu) menuService.getMenuTree();
        String menusString= JSON.toJSONString(menus);
        System.out.println("menusString..."+menusString);
        //复制根菜单
        Menu tMenu=cloneMenu(menus);
        //复制一级菜单
        Menu _m1=null;
        Menu _m2=null;
        for(Menu m1:menus.getMenus()){
            String m1String= JSON.toJSONString(m1);
            System.out.println("m1String..."+m1String);
            _m1=cloneMenu(m1);
            //复制二级菜单
            List<Menu> _m2Menuslist=new ArrayList<Menu>();
            for(Menu m2:m1.getMenus()){
                //截取比如二级菜单字符串中的menuid-301,然后判断是否在用户权限菜单字符串中
                String m2_menuid= JSON.toJSONString(m2.getMenuid());
                if(login_menuString.contains(m2_menuid)){
                    _m2=cloneMenu(m2);
                    _m2Menuslist.add(_m2);
                    String _m2Menuslist_m2= JSON.toJSONString(_m2Menuslist);
                    System.out.println("_m2Menuslist_m2..."+_m2Menuslist_m2);
                    //_m1.getMenus().add(_m2);
                }
            }
            if(_m2Menuslist.size()>0){
                _m1.setMenus(_m2Menuslist);
                String _m1String= JSON.toJSONString(_m1);
                System.out.println("_m1String..."+_m1String);
                //如果一级菜单下有二级菜单，则把一级菜单添加到根菜单下
                tMenu.getMenus().add(_m1);
            }else{
                _m1=null;
            }

        }
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(tMenu);
        System.out.println("tMenu..."+listString);
        write(listString);
    }
    //菜单复制，但不复制子菜单
    private Menu cloneMenu(Menu src){
        Menu _new=new Menu();
        _new.setIcon(src.getIcon());
        _new.setMenuid(src.getMenuid());
        _new.setMenuname(src.getMenuname());
        _new.setUrl(src.getUrl());
        _new.setMenus(new ArrayList<Menu>());
        return _new;
    }


    //公共代码段-1
    public void write(String jsonString) {
        try {
            //响应对象
            response.setContentType("application/json;charset=utf-8");
            //输出页面
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //公共代码段-2
    public void ajax_return(Boolean blean,String message) {
        //返回前端的JSON数据
        Map<String,Object> rtn=new HashMap<String,Object>();
        rtn.put("success",blean);
        rtn.put("message",message);
        write(JSON.toJSONString(rtn));
    }
    //公共代码段-3
    /**
     * //{"name":"管理员组","tele":"000011","uuid":1}
     * @param jsonString JSON数据字符串
     * @param prefix 要加上的前缀
     * @return  {"dep.name":"管理员组","dep.tele":"000011","dep.uuid":1}
     */
    public String mapData(String jsonString, String prefix){
        Map<String, Object> map = JSON.parseObject(jsonString);
        //存储key加上前缀后的值
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //给每key值加上前缀
        for(String key : map.keySet()){
            dataMap.put(prefix + "." + key, map.get(key));
        }
        return JSON.toJSONString(dataMap);
    }



}

