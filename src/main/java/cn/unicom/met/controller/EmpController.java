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
public class EmpController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private EmpService empService;

    @GetMapping("/emp")
    public String log(){
        return "emp";
    }
    /**
     * 部门信息列表
     */
//    @GetMapping(value="/getList")
//    @ResponseBody
//    public void list(){
//        List<Emp> list = empService.getList();
//        //把部门列表转JSON字符串
//        String listString= JSON.toJSONString(list);
//        System.out.println(listString);
//        write(listString);
//    }
    // 查询单个部门，属性驱动，条件查询
    @GetMapping("/get_emp")
    @ResponseBody
    public void getEmp(HttpServletRequest request) {
//        int id=Integer.parseInt(request.getParameter("id"));
        String id=request.getParameter("id");
        logger.info("id" + id);
        Emp emp=empService.get_emp(id);
        //与emp.html中edit_form中的标签一一对应，uuid/name/tele
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uuid",emp.getUuid());
        map.put("username",emp.getUsername());
        map.put("pwd",emp.getPwd());
        map.put("name",emp.getName());
        map.put("gender",emp.getGender());
        map.put("email",emp.getEmail());
        map.put("tele",emp.getTele());
        map.put("depuuid",emp.getDepuuid());
        map.put("post",emp.getPost());
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(map);
        write(listString);
    }

    //条件查询,分页查询
    @GetMapping(value = "/emp_page")
    @ResponseBody
    public Map<String,Object> empByPage(HttpServletRequest request,
                                        @RequestParam("page") String page_str,
                                        @RequestParam("rows") String pageSziestr) {
        //提取url中的page和rows参数值
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageSzie=Integer.parseInt(request.getParameter("rows"));
        int page=Integer.parseInt(page_str);
        int pageSzie=Integer.parseInt(pageSziestr);
        System.out.println("page:"+page+" ----pageSzie:"+pageSzie);
        int startRecord=(page-1)*pageSzie;

        int total=empService.getCount();
        System.out.println("total:"+total);
        List<Emp> emppage=empService.emp_page(startRecord,pageSzie);
        System.out.println(emppage);
        //与emp.html中的标签一一对应，total/rows
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("total",total);
        resultMap.put("rows",emppage);
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }
    /**
     * 新增部门
     */
    @PostMapping("/add_emp")
    @ResponseBody
    public void add(HttpServletRequest request,
                    @RequestBody String jsonstr) {
        //前端url参数传来的就是json字符串
        System.out.println("add_emp前端传来的jsonstr..."+jsonstr);
        //把url参数转为json字符串
//        String jsonstr=getJsonStrByQueryUrl(str);
//        System.out.println("jsonstr...."+jsonstr);
        //下面是把拿到的json字符串转成 java对象
        Emp emp = JSONObject.parseObject(jsonstr, Emp.class);
        //提取url中的page和rows参数值
        String name=emp.getName();
        String tele=emp.getTele();
        logger.info("emp对象:" +emp);
        logger.info("搜索name:" +name+ "     搜索tele..." + tele);

        empService.add_emp(emp);
        //转换成json字符串，输出到页面
        //与emp.html中的标签一一对应，uuid/name/tele
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("uuid",emp.getUuid());
        map.put("username",emp.getUsername());
        map.put("pwd",emp.getPwd());
        map.put("name",emp.getName());
        map.put("gender",emp.getGender());
        map.put("email",emp.getEmail());
        map.put("tele",emp.getTele());
        map.put("depuuid",emp.getDepuuid());
        map.put("post",emp.getPost());
        String json1= JSON.toJSONString(map);
        System.out.println(json1);
        write(json1);
    }

    /**
     * 修改部门
     */
    @PostMapping("/update_emp")
    @ResponseBody
    public void update(@RequestBody String jsonstr)  {
        //保存数据操作
        //下面是把拿到的json字符串转成 java对象
        Emp emp = JSONObject.parseObject(jsonstr, Emp.class);
        empService.update_emp(emp);
        //转换成json字符串，输出到页面
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("uuid",emp.getUuid());
        map.put("username",emp.getUsername());
        map.put("pwd",emp.getPwd());
        map.put("name",emp.getName());
        map.put("gender",emp.getGender());
        map.put("email",emp.getEmail());
        map.put("tele",emp.getTele());
        map.put("depuuid",emp.getDepuuid());
        map.put("post",emp.getPost());
        String json1= JSON.toJSONString(map);
        System.out.println(json1);
        write(json1);
    }

    /**
     * 删除部门
     */
    @PostMapping("/emp_delete")
    @ResponseBody
    public void delete(HttpServletRequest request,
                    @RequestParam("id") String uuid
    ) throws IOException {
        //保存数据操作
        System.out.println("=========uuid====="+uuid);
        empService.del_emp(uuid);
        //转换成json字符串，输出到页面
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        String json1= JSON.toJSONString(map);
        System.out.println(json1);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json1);

    }
    //=========================================================
// 搜索查询部门，前端界面使用get方式时需要加emp.或者t.，后端直接用实体参数就可以接收，不需要@RequestParm @RequestParam("page") String pagestr,
//    @GetMapping(value = "/find_emp")
    @PostMapping(value = "/find_emp",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object>  findEmp(HttpServletRequest request) {
        //前端url参数传来的json字符串
        //String jsonstr=getJsonStrByQueryUrl(str);
        //下面是把拿到的json字符串转成 json对象
//        JSONObject json = JSONObject.parseObject(jsonstr); //将字符串{“id”：1}
//        json.remove("page");
//        json.remove("rows");
//        String jsonrst=json.toString();
        //int jsID = Integer.parseInt(jsStr.getString("id"));//获取id的值
        //json对象转换成java对象
//        Emp emp = JSONObject.parseObject(jsonrst, Emp.class);
        //提取url中的page和rows参数值
//        String name=emp.getName();
//        String uuid=emp.getUuid();
//        String tele=emp.getTele();
//        logger.info("搜索name:" +name+ "     搜索tele..." + tele);
        String name=request.getParameter("name");
        String tele=request.getParameter("tele");
        logger.info("搜索name------" + name+"搜索tele--------" + tele);

        int total=empService.findCount(name,tele);
        System.out.println("total========" + total);
        //提取url中的page和rows参数值
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));
        System.out.println("page:" + page + " ----pageSzie:" + pageSzie);
        int startRecord = (page - 1) * pageSzie;

        List<Emp> find_empList = empService.find_emp(name,tele,startRecord, pageSzie);
        System.out.println(find_empList);
        //与emp.html中的标签一一对应，total/rows
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", total);
        resultMap.put("rows", find_empList);
        return resultMap;

    }

    /**
     * 更新用户密码emp_updatePwd
     * @param
     */
    @PostMapping("/emp_updatePwd")
    @ResponseBody
    public void emp_updatePwd(HttpSession session, HttpServletRequest request ) {
        //下面是把拿到的json字符串转成 java对象
        String nPwd=request.getParameter("newPwd");
        Emp emp1= (Emp) session.getAttribute("login_emp");
//        JSON jsonpwd = (JSON) JSON.parse(jsonstr);
//        JSONObject jsonpwd = (JSONObject) JSON.parse(jsonstr);
        System.out.println("newPwd..."+nPwd);
        emp1.setPwd(nPwd);
        empService.update_emp(emp1);
        //转换成json字符串，输出到页面
        ajax_return(true,"更新成功");

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

    //公共代码4
    /**
     * java url参数转json
     */
//    public static String getJsonStrByQueryUrl(String paramStr){
//        //String paramStr = "a=a1&b=b1&c=c1";
//        String[] params = paramStr.split("&");
//        JSONObject obj = new JSONObject();
//        for (int i = 0; i < params.length; i++) {
//            String[] param = params[i].split("=");
//            System.out.println("params[i]..."+params[i]);
//            if (param.length >= 2) {
//                String key = param[0];
//                String value = param[1];
//                for (int j = 2; j < param.length; j++) {
//                    value += "=" + param[j];
//                }
//                try {
//                    obj.put(key,value);
//                    System.out.println("key..."+key+"value..."+value);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }else {
//                String key = param[0];
//                String value = "";
//                for (int j = 2; j < param.length; j++) {
//                    value += "=" + param[j];
//                }
//                try {
//                    obj.put(key,value);
//                    System.out.println("key..."+key+"value..."+value);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return obj.toString();
//    }

}

