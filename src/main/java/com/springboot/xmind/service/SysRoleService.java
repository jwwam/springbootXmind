package com.springboot.xmind.service;

import com.springboot.xmind.entity.user.SysRole;
import com.springboot.xmind.entity.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysRoleService {
	
	
    UserInfo findById(int id);
    
	Page<SysRole> findByRole(String role, Pageable pageable);

	Page<SysRole> findAll(Pageable pageable);
	
	int updateState(boolean available, String id);
	
	int updateRole(SysRole sr);
	
	SysRole save(SysRole sr);
	    
    
    public SysRole findByOne(String id);
    
    List<SysRole> findAll();

}