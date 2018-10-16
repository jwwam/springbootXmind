package com.springboot.xmind.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: HttpUtils
 * @Auther: zhangyingqi
 * @Date: 2018/10/15 15:37
 * @Description:
 */
public class HttpUtils {

    /**
     * 判断地址是否有效
     * @param url
     * @return
     */
    public static Boolean testURL(String url){
        try {
            URL u = new URL(url);
            try {
                HttpURLConnection uConnection = (HttpURLConnection) u.openConnection();
                uConnection.setConnectTimeout(300000);
                uConnection.setReadTimeout(300000);
                try {
                    uConnection.connect();
                    int code = uConnection.getResponseCode();
                    if(code==404||code==403||code==500||code==400||code==401){
                        return false;
                    }else{
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("connect failed");
                }
            } catch (IOException e) {
                System.out.println("build failed");
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            System.out.println("build url failed");
            e.printStackTrace();
        }
        return false;
    }

    public static String sendGetDownload(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            //connection.setRequestProperty("accept", "*/*");
            //connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            /*Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36*/
            //360:
            //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36

            // 建立实际的连接
            connection.connect();
            System.out.println("正在获取真实下载地址...");
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            System.out.println("获取成功！正在组装下载地址...");
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, String param) {
        String result = "";
        String urlNameString = null;
        BufferedReader in = null;
        try {
            if(param.equals("")||param==null){
                urlNameString = url;
            }else{
                urlNameString = url + "?" + param;
            }
            System.out.println("最终请求地址为："+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            //connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            connection.setRequestProperty("Connection", "Keep-Alive");
            //connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", "_jsuid=2324484325");
            connection.setRequestProperty("Host", "www.xmind.net");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
            /*Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36*/
            // 建立实际的连接
            connection.connect();
            System.out.println("正在获取连接...");
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            System.out.println("连接成功！正在组装数据...");
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8") );
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception httpe) {
            System.out.println("发送GET请求出现异常！" + httpe);
            httpe.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("组装结束，返回数据成功！");
        return result;
    }


}
