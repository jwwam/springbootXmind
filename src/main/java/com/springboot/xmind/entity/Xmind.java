package com.springboot.xmind.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "xmind")
@Data
public class Xmind extends BaseEntity  {

    private String idname;

    private Long userid;

    private String topic;

    private String featured;

    private String previewurl;

    private String thumbnailurl;

    private String backgroundColor;

    private String cxm;

    private String downloadable;

    private Integer downloads;

    private String lang;

    private String description;

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