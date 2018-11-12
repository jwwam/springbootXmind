package com.springboot.xmind.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "xmind")
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

    @Lob
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

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getCxm() {
        return cxm;
    }

    public void setCxm(String cxm) {
        this.cxm = cxm;
    }

    public String getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(String downloadable) {
        this.downloadable = downloadable;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public Date getFormatedTime() {
        return formatedTime;
    }

    public void setFormatedTime(Date formatedTime) {
        this.formatedTime = formatedTime;
    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Override
    public String toString() {
        return "Xmind{" +
                "idname='" + idname + '\'' +
                ", userid=" + userid +
                ", topic='" + topic + '\'' +
                ", featured='" + featured + '\'' +
                ", previewurl='" + previewurl + '\'' +
                ", thumbnailurl='" + thumbnailurl + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", cxm='" + cxm + '\'' +
                ", downloadable='" + downloadable + '\'' +
                ", downloads=" + downloads +
                ", lang='" + lang + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                ", deleted='" + deleted + '\'' +
                ", completed='" + completed + '\'' +
                ", auth='" + auth + '\'' +
                ", profilename='" + profilename + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", created=" + created +
                ", daily='" + daily + '\'' +
                ", formatedTime=" + formatedTime +
                ", gravatar='" + gravatar + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}