package com.springboot.xmind.service.impl;

import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Time;
import com.springboot.xmind.service.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {
	
/*	@Resource
	private TimeMapper timeDao;*/

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		//timeDao.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public int insert(Time record) {
		// TODO Auto-generated method stub
		//timeDao.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(Time record) {
		// TODO Auto-generated method stub
		//timeDao.insertSelective(record);
		return 0;
	}

	@Override
	public Time selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		//return timeDao.selectByPrimaryKey(id);
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Time record) {
		// TODO Auto-generated method stub
		//timeDao.updateByPrimaryKeySelective(record);
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Time record) {
		// TODO Auto-generated method stub
		//timeDao.updateByPrimaryKey(record);
		return 0;
	}

	@Override
	public List<Time> getAllTime() {
		// TODO Auto-generated method stub
		//return timeDao.getAllTime();
		return null;
	}

	public int findTotal() {
		int count = 0;
		//return timeDao.findTotal();
		return 0;
	}
	
	@Override
	public PageBean<Time> getPageBean(int pageNo, int pageSize) {
		PageBean<Time> pb = new PageBean<Time>();
		try {
			int count = findTotal();
			List<Time> list = findList(pageNo, pageSize);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotal(count);
			pb.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
		return pb;
	}
	
	@Override
	public PageBean<Time> getPageBeanByHot(int pageNo, int pageSize) {
		PageBean<Time> pb = new PageBean<Time>();
		try {
			int count = findTotal();
			List<Time> list = findListByHot(pageNo, pageSize);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotal(count);
			pb.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
		return pb;
	}
	
	public List<Time> findList(int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return timeDao.findList(pageNo, pageSize);
		return null;
	}
	
	public List<Time> findListByHot(int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return timeDao.findListByHot(pageNo, pageSize);
		return null;
	}

	@Override
	public Time getLast() {
		//return timeDao.getLast();
		return null;
	}


	@Override
	public int findTotalByLang(String lang) {
		int count = 0;
		//return timeDao.findTotalByLang(lang);
		return 0;
	}

	@Override
	public List<Time> findListByLang(String lang, int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return timeDao.findListByLang(lang, pageNo, pageSize);
		return null;
	}
	
	@Override
	public PageBean<Time> getPageBeanByLang(String lang, int pageNo, int pageSize) {
		PageBean<Time> pb = new PageBean<Time>();
		try {
			int count = findTotalByLang(lang);
			List<Time> list = findListByLang(lang, pageNo, pageSize);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotal(count);
			pb.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return pb;
	}
	
	
}
