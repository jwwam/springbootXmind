package com.springboot.xmind.entity;

import java.math.BigInteger;

/**
 * @ClassName: XmindVo
 * @Auther: zhangyingqi
 * @Date: 2018/10/22 14:35
 * @Description:
 */
public class XmindVo {
    private String username;
    private String gravatar;
    private BigInteger downloads;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public BigInteger getDownloads() {
        return downloads;
    }

    public void setDownloads(BigInteger downloads) {
        this.downloads = downloads;
    }
}
