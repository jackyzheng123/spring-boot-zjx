package com.zjx.controller;

import com.zjx.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yangyibo on 17/1/18.
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mav) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        mav.addObject("msg", msg);
        mav.setViewName("home");
        return mav;
    }
}
