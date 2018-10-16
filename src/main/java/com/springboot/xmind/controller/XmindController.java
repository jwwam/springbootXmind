package com.springboot.xmind.controller;

import com.springboot.xmind.base.common.DataTablesParam;
import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.BuildPageRequest;
import com.springboot.xmind.base.utils.HttpUtils;
import com.springboot.xmind.base.utils.StateParameter;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/xmind")
public class XmindController extends BaseController{

	@Autowired
    private XmindService xmindService;

	@RequestMapping(value="/view")
	public String auth(HttpServletRequest request){
		return "/xmind/xmindList";
	}

	@RequestMapping("/getXmindList")
	@ResponseBody
	public ModelMap getXmindList(DataTablesParam dataTablesParam, String topic, String language){
		ModelMap map = new ModelMap();
		Page<Xmind> pageRe = xmindService.findAll(topic, language, BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "created"));
		map.put("sEcho", dataTablesParam.getsEcho());
		map.put("iTotalRecords", pageRe.getTotalElements());
		map.put("iTotalDisplayRecords",pageRe.getTotalElements());
		map.put("aaData", pageRe.getContent());
		return map;
	}

	@RequestMapping("/getXmindHotList")
	@ResponseBody
	public ModelMap getXmindHotList(DataTablesParam dataTablesParam, String topic, String language){
		ModelMap map = new ModelMap();
		Page<Xmind> pageRe = xmindService.findAll(topic, language, BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "downloads"));
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

	@RequestMapping(value = "getDownloadurl", method= RequestMethod.POST)
	@ResponseBody
	public ModelMap getDownloadurl(String idname){
		String url = "https://www.xmind.net/share/api/maps/" + idname + "/downloadurl";
		//请求该地址，获取真实下载地址
		String result = HttpUtils.sendGetDownload(url);
		//返回json转换
		JSONObject json =JSONObject.fromObject(result);
		//获取返回json
		//JSONObject links = (JSONObject) json.get("links");
		//取出链接
		//String realurl = links.get("default").toString();
		String realurl = json.get("downloadUrl")+"";
		return  getModelMap(StateParameter.SUCCESS, realurl,"操作成功");
	}

}
