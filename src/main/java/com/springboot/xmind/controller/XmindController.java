package com.springboot.xmind.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.xmind.base.common.DataTablesParam;
import com.springboot.xmind.base.utils.BuildPageRequest;
import com.springboot.xmind.base.utils.DateUtils;
import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Thumbnails;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.ThumbnailsService;
import com.springboot.xmind.service.XmindService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/xmind")
public class XmindController {

	@Autowired
    private XmindService xmindService;

	@RequestMapping(value="/view")
	public String auth(HttpServletRequest request){
		return "/xmind/xmindList";
	}

	@RequestMapping("/getXmindList")
	@ResponseBody
	public ModelMap getXmindList(DataTablesParam dataTablesParam, String language, String key){
		ModelMap map = new ModelMap();
		Page<Xmind> pageRe = xmindService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"), language, key);
		map.put("sEcho", dataTablesParam.getsEcho());
		map.put("iTotalRecords", pageRe.getTotalElements());
		map.put("iTotalDisplayRecords",pageRe.getTotalElements());
		map.put("aaData", pageRe.getContent());
		return map;
	}

	@RequestMapping("/getXmindHotList")
	@ResponseBody
	public ModelMap getXmindHotList(DataTablesParam dataTablesParam, String language, String key){
		ModelMap map = new ModelMap();
		Page<Xmind> pageRe = xmindService.findAllByHot(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"), language, key);
		map.put("sEcho", dataTablesParam.getsEcho());
		map.put("iTotalRecords", pageRe.getTotalElements());
		map.put("iTotalDisplayRecords",pageRe.getTotalElements());
		map.put("aaData", pageRe.getContent());
		return map;
	}

	/**
	 * 预览
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/views")
	public String views(HttpServletRequest request, HttpServletResponse response, Model model){
		String viewurl = request.getParameter("viewsurl");
		model.addAttribute("viewurl", viewurl);
		return "viewsForm";
	}
    
}
