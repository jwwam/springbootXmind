package com.springboot.xmind.controller;

import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.ThumbnailsService;
import com.springboot.xmind.service.XmindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/author")
public class Author {
	@Resource    
    private XmindService xmindService;
	@Resource
	private ThumbnailsService thumbnailsService;
	
	public int count = 0;
	
	
	@RequestMapping("/getAuthorInfo")
	public String getAuthorInfo(@RequestParam("authorName") String authorName, HttpServletRequest request, HttpServletResponse response, Model model){
		//发布数
		
		//发布列表
		try {
			String sPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			int pageSize = 25;
			if (sPageNo != null) {
				pageNo = Integer.parseInt(sPageNo);
			} 
			PageBean<Xmind> authorXmindList = xmindService.getPageBeanByAuthor(authorName, pageNo, pageSize);
			model.addAttribute("authorXmindList", authorXmindList);
		} catch (Exception e) {
		}
		return "authorInfo";
	}

    
}
