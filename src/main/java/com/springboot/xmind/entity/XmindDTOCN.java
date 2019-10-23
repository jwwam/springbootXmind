package com.springboot.xmind.entity;

import javax.persistence.Lob;

public class XmindDTOCN {

    //模板主题
    private String 主题;

    //缩略图
    private String 预览地址;

    //下载量
    //private Integer downloads;

    @Lob
    private String 描述;

    //预览量
    //private Integer views;


    public String get主题() {
        return 主题;
    }

    public void set主题(String 主题) {
        this.主题 = 主题;
    }

    public String get预览地址() {
        return 预览地址;
    }

    public void set预览地址(String 预览地址) {
        this.预览地址 = 预览地址;
    }

    public String get描述() {
        return 描述;
    }

    public void set描述(String 描述) {
        this.描述 = 描述;
    }

    @Override
    public String toString() {
        return "XmindDTOCN{" +
                "主题='" + 主题 + '\'' +
                ", 预览地址='" + 预览地址 + '\'' +
                ", 描述='" + 描述 + '\'' +
                '}';
    }
}