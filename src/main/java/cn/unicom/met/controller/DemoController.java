package cn.unicom.met.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
public class DemoController {
    @GetMapping("/demo")
    public String demo() {

        return "add_meeting";
    }

}

