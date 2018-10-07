package com.springboot.xmind.controller;

import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.StateParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: FirstController
 * @Auther: zhangyingqi
 * @Date: 2018/8/23 17:53
 * @Description: 测试页面跳转
 */
@Controller
@RequestMapping(value="/first")
public class FirstController extends BaseController{

    @RequestMapping(value="/view")
    public String view(HttpServletRequest request){
        logger.info("进入测试页面");
        return "/xmind/xmindList";
    }

    @RequestMapping(value="/back", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap back(HttpServletRequest request){
        logger.info("进入json测试页面");
        return getModelMap(StateParameter.SUCCESS, null, "请求成功");
    }

}
