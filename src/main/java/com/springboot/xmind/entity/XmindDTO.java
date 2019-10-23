package com.springboot.xmind.entity;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class XmindDTO{

    //模板主题
    private String topic;


    //预览链接
    private String previewurl;

    //缩略图
    private String thumbnailurl;


    //下载量
    private Integer downloads;


    @Lob
    private String description;

    //预览量
    private Integer views;


}