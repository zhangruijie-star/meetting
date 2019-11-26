package cn.unicom.met.controller;

import cn.unicom.met.entity.Emp;
import cn.unicom.met.entity.EmpMenu;
import cn.unicom.met.entity.Tree;
import cn.unicom.met.service.EmpMenuService;
import cn.unicom.met.service.EmpService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class EmpMenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private EmpMenuService empmenuService;

    @Autowired
    private EmpService empService;
    // 查询用户tree
    @PostMapping(value = "/getEmpMenuTree",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getEmpMenu() {
        //原来的所有菜单
        EmpMenu root= (EmpMenu) empmenuService.getEmpMenuTree();
        String rootString= JSON.toJSONString(root);
        System.out.println("rootString..."+rootString);
        List<Tree> treelist=new ArrayList<Tree>();

        //构建tree集合数据
        Tree t1=null;
        Tree t2=null;
        //把菜单转换成树形结构数据
        for (EmpMenu m1:root.getMenus()){
            t1=new Tree(); //一级菜单
            t1.setId(m1.getMenuid());
            System.out.println("m1.getMenuid()..."+m1.getMenuid());
            t1.setText(m1.getMenuname());
            List<Tree> childlist=new ArrayList<Tree>();
            for(EmpMenu m2:m1.getMenus()){
                t2=new Tree();//二级菜单
                t2.setId(m2.getMenuid());
                System.out.println("m2.getMenuid()..."+m2.getMenuid());
                t2.setText(m2.getMenuname());
                childlist.add(t2);
            }
            t1.setChildren(childlist);
            treelist.add(t1);
        }
        //把用户Tree列表转JSON字符串
        String listString= JSON.toJSONString(treelist);
        System.out.println("EmpMenulist..."+listString);
        write(listString);
    }
    // 查询部门tree
    @PostMapping(value = "/getDepMenuTree",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getDepMenu() {
        //原来的所有菜单
        EmpMenu root= (EmpMenu) empmenuService.getEmpMenuTree();
        String rootString= JSON.toJSONString(root);
        System.out.println("rootString..."+rootString);
        List<Tree> treelist=new ArrayList<Tree>();

        //构建tree集合数据
        Tree t1=null;
        //把菜单转换成树形结构数据
        for (EmpMenu m1:root.getMenus()){
            t1=new Tree(); //一级菜单
            t1.setId(m1.getMenuid());
            System.out.println("m1.getMenuid()..."+m1.getMenuid());
            t1.setText(m1.getMenuname());
            List<Tree> childlist=new ArrayList<Tree>();

            t1.setChildren(childlist);
            treelist.add(t1);
        }
        //把部门Tree列表转JSON字符串
        String listString= JSON.toJSONString(treelist);
        System.out.println("DepMenulist..."+listString);
        write(listString);
    }
    //菜单复制，但不复制子菜单
    private EmpMenu cloneMenu(EmpMenu src){
        EmpMenu _new=new EmpMenu();
        _new.setMenuid(src.getMenuid());
        _new.setMenuname(src.getMenuname());
        _new.setMenus(new ArrayList<EmpMenu>());
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

