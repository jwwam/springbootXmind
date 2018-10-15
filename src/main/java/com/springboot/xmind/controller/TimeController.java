package com.springboot.xmind.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Time;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.TimeService;
import com.springboot.xmind.service.XmindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/timeline")
public class TimeController {
	@Resource    
    private XmindService xmindService;
	@Resource    
	private TimeService timeService;
	
	public int count = 0;

	
	/**
	 * 按时间
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeList")
	public String releasePage(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			String sPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			int pageSize = 25;
			if (sPageNo != null) {
				pageNo = Integer.parseInt(sPageNo);
			} 
			PageBean<Time> timeList = timeService.getPageBean(pageNo, pageSize);
			model.addAttribute("timeList", timeList);
		} catch (Exception e) {
		}
		return "timeLine";
	}
}
