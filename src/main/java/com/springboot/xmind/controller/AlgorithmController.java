package com.springboot.xmind.controller;

import com.springboot.xmind.base.controller.BaseController;
import com.springboot.xmind.base.utils.*;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther: zhangyingqi
 * @date: 9:46 2018/10/16
 * @Description: 接口回执处理部分
 */
@Controller
@RequestMapping("/api")
public class AlgorithmController extends BaseController{

    @Value("${xmind.xmindApiUrl}")
    private String xmindApiUrl;

    @Autowired
    private XmindService xmindService;

    @RequestMapping("/getNewXmind")
    @ResponseBody
    public ModelMap getNewXmind(String start, String end){
        int successNum = 0;
        int failNum = 0;
        int skipNum = 0;
        int sumNum = 0;
        int s = 0;
        int e = 10;//默认获取十条
        Date lastTime = null;
        if(StringUtils.isNotEmpty(start)){
            s = Integer.parseInt(start);
        }
        if(StringUtils.isNotEmpty(end)){
            e = Integer.parseInt(end);
        }
        Page<Xmind> pageRe = xmindService.findLast(BuildPageRequest.getPageRequest(0, 1, Sort.Direction.DESC, "created"));
        if(pageRe.getSize()>0){
            lastTime = pageRe.getContent().get(0).getCreated();
        }else{
            //默认数据库至少有一条数据
            return getModelMap(StateParameter.FAULT, "","获取数据库数据异常!已停止更新");
        }
        StringBuffer sb = new StringBuffer();
        sb.append("lang=ww");
        sb.append("&start=" + s);
        sb.append("&end=" + e);
        String result = "";
        JSONArray maps = new JSONArray();
        //String param = "lang=ww&start=383&end=10000";
        //String url = "https://www.xmind.net/share/api/maps";
        //2018/10/12使用发现官网接口变更为https://www.xmind.net/_api/share/maps
        //考虑到官方可能频繁变动接口地址，所以将其写到配置文件-->xmindApiUrl
        try {
                result = HttpUtils.sendGet(xmindApiUrl, sb.toString());
                //获取所有导图对象
                //maps = (JSONArray) getNewsToJson(result).get("maps");
                //2018/10/12使用发现Key变更为mapList
                maps = (JSONArray) JSONObject.fromObject(result).get("mapList");
        }catch(Exception Jsone){
            System.out.println("本次返回数据异常!");
            Jsone.printStackTrace();
            return getModelMap(StateParameter.FAULT, "","本次返回数据异常!");
        }
            //遍历所有导图
            Iterator<JSONObject> it = maps.iterator();
            while(it.hasNext()){
                ++sumNum;
                //获取导图属性
                JSONObject ob = (JSONObject) it.next();
                Date time = DateUtils.formateCreated(ob.get("created").toString());
                System.out.println("新数据发布时间："+time);
                System.out.println("本次入库前库中最后一条数据发布时间："+lastTime);
                if(time.getTime() > lastTime.getTime()){
                    ModelMap res = setValue(ob);
                    if("1".equals(res.get("status"))){
                        ++successNum;
                    }else{
                        ++failNum;
                    }
                    //2018/10/12使用发现已经没有thumbnails字段了
                    //setThumbnails(ob);
                    //xmind.set...
                }else{ ++skipNum;continue; }

			/*根据时间差计算多久的数据没更新,还没做完，以后再做吧
			 * Date time = isNew(ob.get("created").toString());//2017-07-08T05:49:04
			if(time>getLast().getCreated()){
				xmind.set...
			}else{ continue; }*/
        }
        return getModelMap(StateParameter.SUCCESS, "","本次更新共获取："+sumNum+"条数据，其中成功添加："+successNum+"条数据，添加失败："+failNum+"条数据，跳过："+skipNum+"条数据");
    }

    public ModelMap setValue(JSONObject ob){
        Xmind xmind = new Xmind();
        xmind.setId(UUIDUtils.getUuid());
        try {
            xmind.setUserid(Long.parseLong(ob.get("userId").toString()));
            xmind.setTopic(ob.get("topic").toString());
            xmind.setFeatured(ob.get("featured").toString());
            xmind.setPreviewurl(ob.get("previewUrl").toString());
            xmind.setThumbnailurl(ob.get("thumbnailUrl").toString());
            //2018/10/12使用发现Key变更为backgroundColor
            //xmind.setBackgroundColor(ob.get("background_color").toString());
            xmind.setBackgroundColor(ob.get("backgroundColor").toString());
            xmind.setIdname(ob.get("id").toString());
            xmind.setCxm(ob.get("cxm").toString());
            xmind.setDownloadable(ob.get("downloadable").toString());
            xmind.setCompleted(ob.get("completed").toString());
            xmind.setUsername(ob.get("userName").toString());
            xmind.setDescription(ob.get("description").toString());
            xmind.setViews(Integer.parseInt(ob.get("views").toString()));
            xmind.setDeleted(ob.get("deleted").toString());
            xmind.setDownloads(Integer.parseInt(ob.get("downloads").toString()));
            xmind.setAuth(ob.get("auth").toString());
            xmind.setProfilename(ob.get("profileName").toString());
            xmind.setLang(ob.get("lang").toString());
            xmind.setName(ob.get("name").toString());
            xmind.setFirstname(ob.get("firstName").toString());
            //获取创建时间
            String getCreateTime = ob.get("created").toString();//2017-07-08T05:49:04
            //修改时间
            String ct = getCreateTime.replace("T", " ");
            //格式化时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date createTime = formatter.parse(ct);
                xmind.setCreated(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xmind.setLastname(ob.get("lastName").toString());
            xmind.setDaily(ob.get("daily").toString());
            //下面将原来的转换时间，暂时定义为入库时间
            //xmind.setFormatedTime(new Date());
            try {
                xmind.setFormatedTime(DateUtils.getNowDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xmind.setGravatar(ob.get("gravatar").toString());
            //获取真实下载地址，慎用！频繁请求可能会被关小黑屋，下载地址可能会变动所以不再记录，但是保留此项为以后模板本地保存提供支持
            /*String ralurl = downloadurl(xmind.getIdname());
            if(ralurl=="0"){
                System.out.println("注意：名称为'"+ xmind.getTopic()+"'的XMind资源下载地址无效\n");
                faild++;
                faildList.add(xmind.getTopic());
                xmindService.insert(xmind);
            }else{
                System.out.println("XMind名称："+ xmind.getTopic()+"\n下载地址为："+ ralurl+"\n");
                xmind.setBak1(ralurl);
            }*/
            xmindService.save(xmind);
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, "","本次返回数据异常!");
        }
        return getModelMap(StateParameter.SUCCESS, xmind,"保存成功!");
    }

}
