package cn.unicom.met.controller;

import cn.unicom.met.entity.Emp;
import cn.unicom.met.service.EmpService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//GetMapping 与 PostMapping的区别

/*** GetMapping
 *         //提取url中的page和rows参数值
 * //        int page=Integer.parseInt(request.getParameter("page"));
 * //        int pageSzie=Integer.parseInt(request.getParameter("rows"));
 * **PostMapping
 * public Map<String,Object> empByPage(HttpServletRequest request,
 *                                         @RequestParam("page") String page_str,
 *                                         @RequestParam("rows") String pageSziestr)
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private EmpService empService;
    @GetMapping("/index")
    public String ind(){
        return "index";
    }

    @GetMapping("/login")
    public String log(){
        return "login";
    }

    /**
     * 验证用户
     */
    @PostMapping("/login_checkUser")
    @ResponseBody
    public void ind(HttpSession session, HttpServletRequest request) {
        String uuid=request.getParameter("username");
        String pwd=request.getParameter("pwd");
        System.out.println("uuid.."+uuid+"  ,pwd..."+pwd);
        Emp login_emp=empService.login_emp(uuid,pwd);
        if (login_emp != null) {
            String name=login_emp.getName();
            session.setAttribute("login_emp",login_emp);
            System.out.println("name..."+name);
            ajax_return(true,name);
        }
        else {
            ajax_return(false, "Name or Passowrd Is Erro !");
        }

    }
    /**
     * 退出登陆
     */
    @GetMapping("/login_loginOut")
    @ResponseBody
    public void login_loginOut(HttpSession session) {
        session.removeAttribute("login_emp");
        ajax_return(true,"已安全退出");
    }

    //公共代码段-1
    public void write(String jsonString) {
        try {
            //响应对象
            response.setContentType("text/html;charset=utf-8");
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
     * @return  {"emp.name":"管理员组","emp.tele":"000011","emp.uuid":1}
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

