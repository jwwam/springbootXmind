package com.springboot.xmind.controller;

import com.springboot.xmind.base.utils.DateUtils;
import com.springboot.xmind.base.utils.GetHttpJson;
import com.springboot.xmind.entity.Thumbnails;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.ThumbnailsService;
import com.springboot.xmind.service.XmindService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/set")
public class Algorithm {

    
}
