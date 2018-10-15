package com.springboot.xmind.service.impl;

import com.springboot.xmind.dao.XmindDao;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("xmindService")
public class XmindServiceImpl implements XmindService {

	@Autowired
	XmindDao xmindDao;

	@Override
	public Page<Xmind> findAll(Pageable pageable, String language, String key) {
		return xmindDao.findAll(pageable);
	}

	@Override
	public Page<Xmind> findAllByHot(Pageable pageable, String language, String key) {
		return xmindDao.findAll(pageable);
	}
}
