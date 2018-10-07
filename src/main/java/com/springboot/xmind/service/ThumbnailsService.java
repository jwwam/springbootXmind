package com.springboot.xmind.service;

import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Thumbnails;

import java.util.List;


public interface ThumbnailsService {
	   
	int deleteByPrimaryKey(Long id);

    int insert(Thumbnails record);

    int insertSelective(Thumbnails record);

    Thumbnails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Thumbnails record);

    int updateByPrimaryKey(Thumbnails record);
    
    List<Thumbnails> getAllThumbnails();
    
    PageBean<Thumbnails> getPageBean(int pageNo, int pageSize);
    
    int findTotal();
    
    List<Thumbnails> findList(int pageNo, int pageSize);
    
}
