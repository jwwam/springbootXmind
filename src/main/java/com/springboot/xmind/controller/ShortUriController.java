package com.springboot.xmind.controller;

import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ShortUriController
 * @Auther: zhangyingqi
 * @Date: 2019/10/10 16:50
 * @Description:
 */
@Controller
@RequestMapping("/t")
public class ShortUriController extends BaseController {

    @Autowired
    RedisUtil redis;

    @RequestMapping(value = "/{param}", method=RequestMethod.GET)
    @ResponseBody
    public String getRealUri(@PathVariable("param") String param){
        logger.info("——————————短地址下载——————————");
        StringBuffer sb = new StringBuffer();
        sb.append("下载地址：");
        try {
            Object realUri = redis.get(param,2);
            if(realUri!=null){
                sb.append("<a href=\""+ realUri +"\" target=\"_blank\" style=\"text-decoration=none;font-size=22px;\">点我下载</a></br>");
                sb.append("<label style=\"font-color=red;\">PS: 请使用电脑下载，此链接5分钟内有效。</label>");
            }else{
                sb.append("下载地址已过期，请重新获取！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

}