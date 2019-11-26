package cn.unicom.met.controller;

import cn.unicom.met.entity.Emp;
import cn.unicom.met.entity.HandleRecord;
import cn.unicom.met.entity.Meeting;
import cn.unicom.met.entity.Topic;
import cn.unicom.met.service.HandleRecordService;
import cn.unicom.met.service.MeetingService;
import cn.unicom.met.service.TopicService;
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
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//GetMapping 与 PostMapping的区别

/*** GetMapping
 *         //提取url中的page和rows参数值
 * //        int page=Integer.parseInt(request.getParameter("page"));
 * //        int pageSzie=Integer.parseInt(request.getParameter("rows"));
 * **PostMapping
 * public Map<String,Object> meetingByPage(HttpServletRequest request,
 *                                         @RequestParam("page") String page_str,
 *                                         @RequestParam("rows") String pageSziestr)
 */
@Controller
public class MeetingController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private HandleRecordService handleRecordService;

    @GetMapping("/addmet")
    public String addmet(){
        return "addmet";
    }

    @GetMapping("/selfmet")
    public String selfmet(){
        return "selfmet";
    }

    @GetMapping("/nextdealmet")
    public String nextdealmet(){
        return "nextdealmet";
    }
    /**
     * 部门信息列表
     */
//    @GetMapping(value="/getList")
//    @ResponseBody
//    public void list(){
//        List<Meeting> list = meetingService.getList();
//        //把部门列表转JSON字符串
//        String listString= JSON.toJSONString(list);
//        System.out.println(listString);
//        write(listString);
//    }
    // 查询单个部门，属性驱动，条件查询
    @GetMapping("/get_meeting")
    @ResponseBody
    public void getMeeting(HttpServletRequest request) {
//        int id=Integer.parseInt(request.getParameter("id"));
        String id=request.getParameter("id");
        logger.info("id" + id);
        Meeting meeting=meetingService.get_meeting(id);
        //与meeting.html中edit_form中的标签一一对应，uuid/name/mtype
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uuid",meeting.getUuid());
        map.put("mtitle",meeting.getMtitle());
        map.put("mtype",meeting.getMtype());
        map.put("address",meeting.getAddress());
        map.put("start_datetime",meeting.getStart_time());
        map.put("end_datetime",meeting.getEnd_time());
        map.put("status",meeting.getStatus());
        map.put("create_uuid",meeting.getCreate_uuid());
        map.put("next_deal_uuid",meeting.getNext_deal_uuid());
        map.put("mtext",meeting.getMtext());
        //把部门列表转JSON字符串
        String listString= JSON.toJSONString(map);
        write(listString);
    }

    //条件查询,分页查询
    @GetMapping(value = "/meeting_page")
    @ResponseBody
    public Map<String,Object> meetingByPage(HttpServletRequest request,
                                        @RequestParam("page") String page_str,
                                        @RequestParam("rows") String pageSziestr) {
        //提取url中的page和rows参数值
//        int page=Integer.parseInt(request.getParameter("page"));
//        int pageSzie=Integer.parseInt(request.getParameter("rows"));
        int page=Integer.parseInt(page_str);
        int pageSzie=Integer.parseInt(pageSziestr);
        System.out.println("page:"+page+" ----pageSzie:"+pageSzie);
        int startRecord=(page-1)*pageSzie;

        int total=meetingService.getCount();
        System.out.println("total:"+total);
        List<Meeting> meetingpage=meetingService.meeting_page(startRecord,pageSzie);
        System.out.println(meetingpage);
        //与meeting.html中的标签一一对应，total/rows
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("total",total);
        resultMap.put("rows",meetingpage);
        System.out.println("resultMap:"+resultMap);
        return resultMap;
    }
    /**
     * 新增会议
     */
    @PostMapping("/add_meeting")
    @ResponseBody
    public void add(HttpServletRequest request,
                    @RequestBody String jsonstr) {
        //前端url参数传来的就是json字符串
        System.out.println("add_meeting前端传来的jsonstr..."+jsonstr);
        //把url参数转为json字符串
//        String jsonstr=getJsonStrByQueryUrl(str);
//        System.out.println("jsonstr...."+jsonstr);
        //下面是把拿到的json字符串转成 java对象
        Meeting meeting = JSONObject.parseObject(jsonstr, Meeting.class);

        meetingService.add_meeting(meeting);

        System.out.println("新meeting保存成功");
    }

    /**
     * 修改部门
     */
    @PostMapping("/update_meeting")
    @ResponseBody
    public void update(@RequestBody String jsonstr)  {
        //保存数据操作
        //下面是把拿到的json字符串转成 java对象
        Meeting meeting = JSONObject.parseObject(jsonstr, Meeting.class);
        meetingService.update_meeting(meeting);
        //转换成json字符串，输出到页面
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("uuid",meeting.getUuid());
        map.put("mtitle",meeting.getMtitle());
        map.put("mtype",meeting.getMtype());
        map.put("address",meeting.getAddress());
        map.put("start_datetime",meeting.getStart_time());
        map.put("end_datetime",meeting.getEnd_time());
        map.put("status",meeting.getStatus());
        map.put("create_uuid",meeting.getCreate_uuid());
        map.put("next_deal_uuid",meeting.getNext_deal_uuid());
        map.put("mtext",meeting.getMtext());
        String json1= JSON.toJSONString(map);
        System.out.println(json1);
        write(json1);
    }

    /**
     * 删除会议
     */
    @PostMapping("/meeting_delete")
    @ResponseBody
    public void delete(HttpServletRequest request,
                    @RequestParam("id") String uuid
    ) throws IOException {
        //保存数据操作
        System.out.println("=========uuid====="+uuid);
        meetingService.del_meeting(uuid);
        //转换成json字符串，输出到页面
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("uuid",uuid);
        String json1= JSON.toJSONString(map);
        System.out.println(json1);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json1);

    }
    //==========创建人-我的会议列表======================================
// 搜索查询部门，前端界面使用get方式时需要加meeting.或者t.，后端直接用实体参数就可以接收，不需要@RequestParm @RequestParam("page") String pagestr,
//    @GetMapping(value = "/find_meeting")
    @PostMapping(value = "/find_meeting",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object>  findMeeting(HttpServletRequest request, HttpSession session) {
        //前端url参数传来的json字符串
        //String jsonstr=getJsonStrByQueryUrl(str);
        //下面是把拿到的json字符串转成 json对象
//        JSONObject json = JSONObject.parseObject(jsonstr); //将字符串{“id”：1}
//        json.remove("page");
//        json.remove("rows");
//        String jsonrst=json.toString();
        //int jsID = Integer.parseInt(jsStr.getString("id"));//获取id的值
        //json对象转换成java对象
//        Meeting meeting = JSONObject.parseObject(jsonrst, Meeting.class);
        String mtitle=request.getParameter("mtitle");
        String mtype=request.getParameter("mtype");
        logger.info("搜索mtitle------" + mtitle+"搜索mtype--------" + mtype);
        Emp loginEmp = (Emp) session.getAttribute("login_emp");
        String login_uuid=loginEmp.getUuid();
        System.out.println("login_uuid...."+login_uuid);

        int total=meetingService.findCount(login_uuid,mtitle,mtype);
        System.out.println("total========" + total);
        //提取url中的page和rows参数值
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));
        System.out.println("page:" + page + " ----pageSzie:" + pageSzie);
        int startRecord = (page - 1) * pageSzie;

        List<Meeting> find_meetingList = meetingService.find_meeting(login_uuid,mtitle,mtype,startRecord, pageSzie);

        String fdmeeting_str=JSON.toJSONString(find_meetingList);
        System.out.println("fdmeeting_str...."+fdmeeting_str);
        //与meeting.html中的标签一一对应，total/rows
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", total);
        resultMap.put("rows", find_meetingList);
        return resultMap;

    }
    //=======审核人-待处理会议列表===============================
// 搜索查询部门，前端界面使用get方式时需要加meeting.或者t.，后端直接用实体参数就可以接收，不需要@RequestParm @RequestParam("page") String pagestr,
    @PostMapping(value = "/find_next_meeting",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object>  find_next_meeting(HttpServletRequest request, HttpSession session) {
        String mtitle=request.getParameter("mtitle");
        String mtype=request.getParameter("mtype");

        Emp loginEmp = (Emp) session.getAttribute("login_emp");
        String login_uuid=loginEmp.getUuid();
        logger.info("login_uuid------" + login_uuid);
        int total=meetingService.findNextCount(login_uuid,mtitle,mtype);
        System.out.println("total========" + total);
        //提取url中的page和rows参数值
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));
        System.out.println("page:" + page + " ----pageSzie:" + pageSzie);
        int startRecord = (page - 1) * pageSzie;

        List<Meeting> find_meetingList = meetingService.findNextMeeting(login_uuid,mtitle,mtype,startRecord, pageSzie);
        System.out.println(find_meetingList);
        //与meeting.html中的标签一一对应，total/rows
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", total);
        resultMap.put("rows", find_meetingList);
        return resultMap;
    }
    /**
     * 创建人保存及提交会议
     */
    @PostMapping("/submit_meeting")
    @ResponseBody
    public void submit(@RequestBody String jsonstr)  {
        //对前端提交的字符串进行截取，前半段为会议，后半段为议题
        //找到议题第一次出现的位置
        System.out.println("前端提交的jsonstr..."+jsonstr);
        int tip_first=jsonstr.indexOf("topic_uuid");
        System.out.println("议题出现的位置..."+tip_first);
        if(tip_first<0){
            Meeting meeting = JSONObject.parseObject(jsonstr, Meeting.class);
            meetingService.add_meeting(meeting);
            //下面是把拿到的json字符串转成 java对象
            meeting.setStatus("已提交");
            meetingService.update_meeting(meeting);
        }
        if(tip_first>10){
            //截取会议字符串
            String met_str=jsonstr.substring(0, tip_first-2)+"}";
            System.out.println("会议字符串..."+met_str);
            //截取议题字符串
            String tip_str="{"+jsonstr.substring(tip_first-1, jsonstr.length());
            System.out.println("截取议题字符串..."+tip_str);
            //保存会议数据操作
            Meeting meeting = JSONObject.parseObject(met_str, Meeting.class);
            meetingService.add_meeting(meeting);
            //下面是把拿到的json字符串转成 java对象
            meeting.setStatus("已提交");
            meetingService.update_meeting(meeting);
            System.out.println("met_str..."+met_str);
            System.out.println("tip_str..."+tip_str);
            //处理议题字符串
            int i=1;
            while (i<=20){
                i++;
                if (tip_str.contains("comment")) {
                    //查找topic_uuid出现的位置，如果大于10，说明至少有两个议题
                    int tip_num=tip_str.indexOf("topic_uuid", tip_str.indexOf("topic_uuid") + 2);
                    System.out.println("tip_num..."+tip_num);
                    if(tip_num>20){
                        //重载索引，查找第二次出现topic_uuid的位置
                        int comment_first=tip_str.indexOf("topic_uuid", tip_str.indexOf("topic_uuid") + 2);
                        System.out.println("comment_first..."+comment_first);
                        //组装第一个议题 字符串
                        String newtip_str=tip_str.substring(0, comment_first-2)+"}";
                        //打印
                        System.out.println("newtip_str..."+newtip_str);
                        //保存这个议题对象
                        Map<String, Object> map = JSONObject.parseObject(newtip_str);
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("met_uuid",meeting.getUuid());
                        for(String key : map.keySet()){
                            //去掉key中的数字
                            dataMap.put(key.replaceAll("\\d+", ""), map.get(key));
                        }
                        String topic_jsonstr=JSON.toJSONString(dataMap);
                        System.out.println("topic_jsonstr...."+topic_jsonstr);
                        Topic topic = JSONObject.parseObject(topic_jsonstr, Topic.class);
                        topicService.add_topic(topic);
                        System.out.println("新topic保存成功");
                        //清空map，接着下一次组装
                        dataMap.clear();
                        //去掉第一个议题字符串，留下剩余的
                        tip_str="{"+tip_str.substring(comment_first-1, tip_str.length());
                        System.out.println("新的tip_str...."+tip_str);
//                        continue;
                    }else {
                        //如果还有comment,说明是最后一个议题对象
                        Map<String, Object> map = JSONObject.parseObject(tip_str);
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("met_uuid",meeting.getUuid());
                        for(String key : map.keySet()){
                            //去掉key中的数字
                            dataMap.put(key.replaceAll("\\d+", ""), map.get(key));
                        }
                        String topic_jsonstr=JSON.toJSONString(dataMap);
                        System.out.println("topic_jsonstr...."+topic_jsonstr);
                        Topic topic = JSONObject.parseObject(topic_jsonstr, Topic.class);
                        topicService.add_topic(topic);
                        System.out.println("新topic保存成功");
                        //清空map，接着下一次组装
                        dataMap.clear();
                        tip_str="";
                    }

                }
            }
        }
    }


    //----会议详情页
    @GetMapping("/detailmet/{uuid}")
    public String view(@PathVariable("uuid") String uuid,Map<String, Object> map) {
        logger.info("查询会议编号为:" + uuid + "的详细信息");

        Meeting meeting=meetingService.get_meeting(uuid);

        map.put("meeting", meeting);
        //议题详情
        List<Topic> topicList = topicService.getList(uuid);
        System.out.println("子议题数量..."+topicList.size());
        String jsonstr=JSON.toJSONString(topicList);
        System.out.println("jsonstr..."+jsonstr);
        map.put("topicList", topicList);
        //处理记录
        List<HandleRecord> handleList = handleRecordService.selectByMetuuid(uuid);
        System.out.println("处理记录数量..."+handleList.size());
        String handle_jsonstr=JSON.toJSONString(handleList);
        System.out.println("handle_jsonstr..."+handle_jsonstr);
        map.put("handleList", handleList);
        // type = null 则进入view.html， type=update 则是进入update.html
        return "detailmet";
    }
    //----查看审核会议这个页面
    @GetMapping("/submit/{uuid}")
    public String submit(@PathVariable("uuid") String uuid,Map<String, Object> map) {
        logger.info("查询会议编号为:" + uuid + "的详细信息");

        Meeting meeting=meetingService.get_meeting(uuid);

        map.put("meeting", meeting);
        //议题详情
        List<Topic> topicList = topicService.getList(uuid);
        System.out.println("子议题数量..."+topicList.size());
        String jsonstr=JSON.toJSONString(topicList);
        System.out.println("jsonstr..."+jsonstr);
        map.put("topicList", topicList);
        //处理记录
        List<HandleRecord> handleList = handleRecordService.selectByMetuuid(uuid);
        System.out.println("处理记录数量..."+handleList.size());
        String handle_jsonstr=JSON.toJSONString(handleList);
        System.out.println("handle_jsonstr..."+handle_jsonstr);
        map.put("handleList", handleList);

        // type = null 则进入view.html， type=update 则是进入update.html
        return "submitmet";
    }

    //处理会议记录
    /**
     * 审核人处理会议
     */
//    @GetMapping("/handle_met")
    @PostMapping(value = "/handle_met",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void handle(@RequestBody String jsonstr){
        //下面是把拿到的json字符串转成 java对象
        System.out.println("submitmet前端的jsonstr..."+jsonstr);
        HandleRecord handleRecord= JSONObject.parseObject(jsonstr, HandleRecord.class);
        System.out.println("审核人处理意见..."+handleRecord.getComment());
        //审核人的信息
        System.out.println("审核人dealuuid...."+handleRecord.getDealuuid());
        //根据前端提交的handleRecord中有metuuid，来查询对应的meeting，并改变状态
        Meeting meeting= meetingService.get_meeting(handleRecord.getMetuuid());
        meeting.setStatus("已审核");
        meetingService.update_meeting(meeting);

        handleRecord.setDealresult("已审核");
        handleRecordService.addhandlerecord(handleRecord);
        ajax_return(true,"处理完成");
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
     * //{"name":"管理员组","mtype":"000011","uuid":1}
     * @param jsonString JSON数据字符串
     * @param prefix 要加上的前缀
     * @return  {"meeting.name":"管理员组","meeting.mtype":"000011","meeting.uuid":1}
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

