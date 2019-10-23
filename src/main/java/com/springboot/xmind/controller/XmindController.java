package com.springboot.xmind.controller;

import com.springboot.xmind.base.common.DataTablesParam;
import com.springboot.xmind.base.common.GlobalVarManager;
import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.BuildPageRequest;
import com.springboot.xmind.base.utils.HttpUtils;
import com.springboot.xmind.base.utils.StateParameter;
import com.springboot.xmind.base.utils.redis.RedisUtil;
import com.springboot.xmind.base.utils.shorter.generator.StringGeneratorRandom;
import com.springboot.xmind.base.utils.shorter.generator.UrlShorterGeneratorSimple;
import com.springboot.xmind.base.utils.shorter.shorter.ShorterString;
import com.springboot.xmind.base.utils.shorter.storage.ShorterStorageMemory;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.entity.XmindDTO;
import com.springboot.xmind.entity.XmindDTOCN;
import com.springboot.xmind.entity.XmindVo;
import com.springboot.xmind.entity.user.UserInfo;
import com.springboot.xmind.service.XmindService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/center")
public class XmindController extends BaseController{

	@Autowired
    private XmindService xmindService;

	@Autowired
	RedisUtil redisUtil;

	@RequestMapping(value="/index")
	public String index(HttpServletRequest request){
		return "/xmind/index";
	}

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
	public ModelMap getDownloadurl(HttpServletRequest request, String idname){
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		if(user==null){
			return getModelMap(StateParameter.FAULT, "","跳转登录");
		}
		String realurl = getDownloadUri(idname);
		List<Xmind> list = xmindService.findByName(idname);
		//累加本地下载次数
		if (list.size()>0){
			Xmind x = list.get(0);
			x.setLocaldown(x.getLocaldown()+1);
			xmindService.save(x);
		}
		return getModelMap(StateParameter.SUCCESS, realurl,"操作成功");
	}

	@RequestMapping("/getDownList")
	@ResponseBody
	public ModelMap getDownList(){
		//Sort sort = new Sort(Sort.Direction.DESC, "downloads");
		//Pageable pageable = new PageRequest(0, 100, sort);
		List<XmindVo> pageRe = null;
		int pageSize = 30;
		try {
			pageRe = xmindService.getDownTop100(pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getModelMap(StateParameter.SUCCESS, pageRe,"操作成功");
	}


	@RequestMapping(value = "/findByKeyAndLanguage", method=RequestMethod.GET)
	@ResponseBody
	public String findByKeyAndLanguage(DataTablesParam dataTablesParam, String topic, String language, String type){
		StringBuffer sb = new StringBuffer();
		//ModelMap map = new ModelMap();
		if(StringUtils.isEmpty(topic)){
			sb.append("【换行】如需查询思维导图请输入：思维导图+空格+关键字, 如：思维导图 学习");
			return sb.toString();
		}
		Page<Xmind> pageRe = xmindService.findAll(topic, language, BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "created"));
		if(pageRe.getContent().size()==0){
			//map.put("result", "抱歉，未查询到该模板相关信息");
			sb.append("抱歉，未查询到'"+ topic +"'的相关信息.");
		}
		//List<String> resList = new ArrayList();

		sb.append("【换行】");
		for (int i = 0; i < pageRe.getContent().size(); i++) {
			Xmind xm = pageRe.getContent().get(i);

			//XmindDTOCN targetDto = new XmindDTOCN();
			//BeanUtils.copyProperties(pageRe.getContent().get(i), targetDto);
			//targetDto.set主题(xm.getTopic());
			//targetDto.set描述(xm.getDescription());
			//targetDto.set预览地址(xm.getPreviewurl());
			sb.append("【换行】("+ (i+1) +")《"+xm.getTopic()+"》");
			if(StringUtils.isNotEmpty(xm.getDescription())){
				sb.append("，"+xm.getDescription());
			}
			String realUri = getDownloadUri(xm.getIdname());
			UrlShorterGeneratorSimple simple = new UrlShorterGeneratorSimple();
			simple.setGenerator(new StringGeneratorRandom(5));
			simple.setShorterStorage(new ShorterStorageMemory<>());
			String shorter = simple.generate(realUri).getShorter();
			redisUtil.set(shorter, realUri,(long)60*5, 2);
			//wwww.feelcode.cn
			shorter = "www.feelcode.cn/xmind/t/"+shorter;
			if(StringUtils.isNotEmpty(type) && "链接".equals(type)){
				sb.append(
						"【换行】预览地址：【换行】" + xm.getPreviewurl() + "【换行】【换行】" +
								"下载地址：【换行】" + shorter +"【换行】");
			}else{
				//StringUtils.isNotEmpty(type) && "链接".equals(type)
				sb.append(
						//"，" + "预览地址：" + xm.getPreviewurl() +
						//"【换行】图片未上传完则不发出消息[imgurl]" + xm.getPreviewurl() + "[/imgurl]【换行】"+
						"【换行】[imgurl]" + xm.getPreviewurl() + "[/imgurl]【换行】【换行】" +
								"下载地址：【换行】" + shorter +"【换行】");
			}
			//[imgurl]http://www.qqshow123.com/setrobot.jpg[/imgurl]
			//resList.add(sb.toString());
		}
		//map.put("result", sb);
		return sb.toString();
	}

	public String getDownloadUri(String idname){
		String url = "https://www.xmind.net/_api/share/maps/" + idname + "/downloadurl";
		//请求该地址，获取真实下载地址
		String result = HttpUtils.sendGetDownload(url);
		//返回json转换
		JSONObject json =JSONObject.fromObject(result);
		//获取返回json
		//JSONObject links = (JSONObject) json.get("links");
		//取出链接
		//String realurl = links.get("default").toString();
		String realurl = json.get("downloadUrl")+"";
		return realurl;
	}

}
