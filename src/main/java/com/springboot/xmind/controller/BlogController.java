package com.springboot.xmind.controller;

import com.springboot.xmind.base.common.DataTablesParam;
import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.BuildPageRequest;
import com.springboot.xmind.base.utils.StateParameter;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {

    @Autowired
    private XmindService xmindService;

    @RequestMapping(value="/index")
    public String auth(HttpServletRequest request,String name){
        request.setAttribute("username", name);
        return "/xmind/blog";
    }

    @RequestMapping("/getUserXmindList")
    @ResponseBody
    public ModelMap getUserXmindList(DataTablesParam dataTablesParam, String name){
        ModelMap map = new ModelMap();
        Page<Xmind> pageRe = xmindService.findByName(name, BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "created"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return map;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ModelMap getUserInfo(String name){
        Map map = new HashMap();
        List<Xmind> pageRe = xmindService.findByName(name);
        if(pageRe.size()>0){
            map.put("xmind", pageRe.get(0));
            map.put("count", pageRe.size());
            Integer downs = 0;
            Integer views = 0;
            for (int i = 0; i<pageRe.size(); i++) {
                downs += pageRe.get(i).getDownloads();
                views += pageRe.get(i).getViews();
            }
            map.put("downs", downs);
            map.put("views", views);
            return getModelMap(StateParameter.SUCCESS, map, "操作成功");
        }
        return getModelMap(StateParameter.FAULT, "","操作失败");
    }

}