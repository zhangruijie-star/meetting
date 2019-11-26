package cn.unicom.met.controller;


import cn.unicom.met.entity.*;
import cn.unicom.met.service.MenuService;
import cn.unicom.met.service.RoleMenuService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;
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
public class RoleMenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;
    // 获取角色列表
    @PostMapping(value = "/getRoleList")
    @ResponseBody
    public void getRoleList() {
//        int id=Integer.parseInt(request.getParameter("id"));
//        String id=request.getParameter("id");
//        logger.info("id" + id);
        List<Role> rolelist=roleMenuService.getRoleList();
        //与dep.html中edit_form中的标签一一对应，uuid/name/tele
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("menuList",menuList);
//        System.out.println(map);
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(rolelist);
        System.out.println("rolelist..."+listString);
        write(listString);
    }
    // 获取角色菜单列表
    @PostMapping(value = "/getRoleMenu")
    @ResponseBody
    public void getRoleMenu() {

        Menu root= (Menu) menuService.getMenuTree();
        String rootString= JSON.toJSONString(root);
        System.out.println("rootString..."+rootString);
        List<Tree> treelist=new ArrayList<Tree>();
        //构建tree集合数据
        Tree t1=null;
        Tree t2=null;
        //把菜单转换成树形结构数据
        for (Menu m1:root.getMenus()){
            t1=new Tree(); //一级菜单
            t1.setId(m1.getMenuid());
            System.out.println("m1.getMenuid()..."+m1.getMenuid());
            t1.setText(m1.getMenuname());
            List<Tree> childlist=new ArrayList<Tree>();
            for(Menu m2:m1.getMenus()){
                t2=new Tree();//二级菜单
                t2.setId(m2.getMenuid());
                System.out.println("m2.getMenuid()..."+m2.getMenuid());
                t2.setText(m2.getMenuname());
                childlist.add(t2);
            }
            t1.setChildren(childlist);
            treelist.add(t1);
        }
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(treelist);
        System.out.println("roleMenulist..."+listString);
        write(listString);
    }

    // 获取角色对应的权限Tree菜单列表
    @PostMapping(value = "/getRoleMenu/{uuid}")
    @ResponseBody
    public void getRoleIdMenu(@PathVariable("uuid") String roleid) {

        Menu root= (Menu) menuService.getMenuTree();
        String rootString= JSON.toJSONString(root);
        System.out.println("rootString..."+rootString);
        List<Tree> treelist=new ArrayList<Tree>();
        System.out.println("roleid..."+roleid);
        List<RoleMenu> roleMenuList=roleMenuService.getRoleMenu(roleid);
        String rmString= JSON.toJSONString(roleMenuList);
        System.out.println("rolemenuString..."+rmString);
        //构建tree集合数据
        Tree t1=null;
        Tree t2=null;
        //把菜单转换成树形结构数据
        for (Menu m1:root.getMenus()){
            t1=new Tree(); //一级菜单
            t1.setId(m1.getMenuid());
            System.out.println("m1.getMenuid()..."+m1.getMenuid());
            t1.setText(m1.getMenuname());
            List<Tree> childlist=new ArrayList<Tree>();
            for(Menu m2:m1.getMenus()){
                t2=new Tree();//二级菜单
                t2.setId(m2.getMenuid());
                System.out.println("m2.getMenuid()..."+m2.getMenuid());
                t2.setText(m2.getMenuname());
                childlist.add(t2);
                //如果角色中包含m2,设置被选中
                if(rmString.contains(m2.getMenuid())){
                    t2.setChecked(true);
                }else{
                    t2.setChecked(false);
                }
            }
            t1.setChildren(childlist);
            treelist.add(t1);
        }
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(treelist);
        System.out.println("roleMenulist..."+listString);
        write(listString);
    }
    // 更新角色菜单列表
    @PostMapping(value = "/updateRoleMenu")
    @ResponseBody
    public void updateRoleMenu(HttpServletRequest request) {
        String id=request.getParameter("id");
        String checkedStr=request.getParameter("checkedStr");
        System.out.println("checkedStr..."+checkedStr);
        //先删除原来roleid相应的角色菜单
        roleMenuService.deleteRole(id);

        //依次新增角色及相应的权限
        List<String> menuid_list= Arrays.asList(checkedStr.split(","));
        System.out.println("menuid_list..."+menuid_list);
        for(String menuid:menuid_list){
            roleMenuService.addrolemenu(id,menuid);
        }
        ajax_return(true,"更新成功");
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

