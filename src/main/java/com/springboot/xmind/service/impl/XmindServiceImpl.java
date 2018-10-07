package com.springboot.xmind.service.impl;

import com.springboot.xmind.base.utils.PageBean;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XmindServiceImpl implements XmindService {
	
/*	@Resource
	private XmindMapper xmindDao;*/

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		//xmindDao.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public int insert(Xmind record) {
		// TODO Auto-generated method stub
		//xmindDao.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(Xmind record) {
		// TODO Auto-generated method stub
		//xmindDao.insertSelective(record);
		return 0;
	}

	@Override
	public Xmind selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		//return xmindDao.selectByPrimaryKey(id);
		return null;
	}
	
	@Override
	public Xmind selectByIdName(String idName) {
		//return xmindDao.selectByIdName(idName);
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Xmind record) {
		// TODO Auto-generated method stub
		//xmindDao.updateByPrimaryKeySelective(record);
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Xmind record) {
		// TODO Auto-generated method stub
		//xmindDao.updateByPrimaryKey(record);
		return 0;
	}

	@Override
	public List<Xmind> getAllXmind() {
		// TODO Auto-generated method stub
		//return xmindDao.getAllXmind();
		return null;
	}

	public int findTotal() {
		int count = 0;
		//return xmindDao.findTotal();
		return 0;
	}
	
	@Override
	@Cacheable(value = "cache")
	public PageBean<Xmind> getPageBean(int pageNo, int pageSize) {
		PageBean<Xmind> pb = new PageBean<Xmind>();
		try {
			int count = findTotal();
			List<Xmind> list = findList(pageNo, pageSize);
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
	@Cacheable(value = "cache")
	public PageBean<Xmind> getPageBeanByHot(int pageNo, int pageSize) {
		PageBean<Xmind> pb = new PageBean<Xmind>();
		try {
			int count = findTotal();
			List<Xmind> list = findListByHot(pageNo, pageSize);
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
	
	public List<Xmind> findList(int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return xmindDao.findList(pageNo, pageSize);
		return null;
	}
	
	public List<Xmind> findListByHot(int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return xmindDao.findListByHot(pageNo, pageSize);
		return null;
	}

	@Override
	public Xmind getLast() {
		//return xmindDao.getLast();
		return null;
	}


	@Override
	public int findTotalByLang(String lang) {
		int count = 0;
		//return xmindDao.findTotalByLang(lang);
		return 0;
	}

	@Override
	public List<Xmind> findListByLang(String lang, int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return xmindDao.findListByLang(lang, pageNo, pageSize);
		return null;
	}
	
	@Override
	public PageBean<Xmind> getPageBeanByLang(String lang, int pageNo, int pageSize) {
		PageBean<Xmind> pb = new PageBean<Xmind>();
		try {
			int count = findTotalByLang(lang);
			List<Xmind> list = findListByLang(lang, pageNo, pageSize);
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

	@Override
	public int findTotalByKey(String key) {
		int count = 0;
		//return xmindDao.findTotalByKey(key);
		return 0;
	}

	@Override
	public List<Xmind> findListByKey(String key, int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return xmindDao.findListByKey(key, pageNo, pageSize);
		return null;
	}

	@Override
	public PageBean<Xmind> getPageBeanByKey(String key, int pageNo, int pageSize) {
		PageBean<Xmind> pb = new PageBean<Xmind>();
		try {
			int count = findTotalByKey(key);
			List<Xmind> list = findListByKey(key, pageNo, pageSize);
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

	@Override
	public int findTotalByAuthor(String uerName) {
		int count = 0;
		//return xmindDao.findTotalByAuthor(uerName);
		return 0;
	}

	@Override
	public List<Xmind> findListByAuthor(String uerName, int pageNo, int pageSize) {
		pageNo = (pageNo-1)*pageSize;
		//return xmindDao.findListByAuthor(uerName, pageNo, pageSize);
		return null;
	}

	@Override
	public PageBean<Xmind> getPageBeanByAuthor(String uerName, int pageNo, int pageSize) {
		PageBean<Xmind> pb = new PageBean<Xmind>();
		try {
			int count = findTotalByAuthor(uerName);
			List<Xmind> list = findListByAuthor(uerName, pageNo, pageSize);
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
