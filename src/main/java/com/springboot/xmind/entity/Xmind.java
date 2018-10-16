package com.springboot.xmind.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "xmind")
@Data
public class Xmind extends BaseEntity  {

    //模板标识码
    private String idname;

    private Long userid;

    //模板主题
    private String topic;

    private String featured;

    //预览链接
    private String previewurl;

    //缩略图
    private String thumbnailurl;

    private String backgroundColor;

    private String cxm;

    private String downloadable;

    //下载量
    private Integer downloads;

    //语言
    private String lang;

    private String description;

    //预览量
    private Integer views;

    private String deleted;

    private String completed;

    private String auth;

    private String profilename;

    private String username;

    private String name;

    private String firstname;

    private String lastname;

    private Date created;

    private String daily;

    private Date formatedTime;

    private String gravatar;

    private String fileurl;

}