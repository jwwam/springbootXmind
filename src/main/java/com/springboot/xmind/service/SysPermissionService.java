package com.springboot.xmind.service;

import com.springboot.xmind.entity.user.SysPermission;
import com.springboot.xmind.entity.user.SysPermissionVo;

import java.util.List;

public interface SysPermissionService {
	
	
	SysPermission findById(int id);

	List<SysPermissionVo> findAll();
	
	
	SysPermission save(SysPermission sp);
	
	int updatePermission(SysPermission sp); 
	
	
	SysPermission findByOne(String id);
	
	int delete(String id);
}