package com.zjx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description Session共享测试
 * @Author Carson Cheng
 * @Date 2019/3/22 16:39
 * @Version V1.0
 **/

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping(value = "/first")
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("requestUrl", request.getRequestURL());
        map.put("requestUrl", request.getRequestURL());
        return map;
    }

    @GetMapping(value = "/sessions")
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("requestUrl"));
        return map;
    }
}
