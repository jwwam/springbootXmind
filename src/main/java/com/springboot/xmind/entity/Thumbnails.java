package com.springboot.xmind.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "thumbnails")
@Data
public class Thumbnails extends BaseEntity{

    private String idname;

    private String thumbnailsUrl;

    private String keyName;

    private String bucket;

    private Long mapId;

    private String size;

    private String bak1;

    private String bak2;

    private String bak3;


}