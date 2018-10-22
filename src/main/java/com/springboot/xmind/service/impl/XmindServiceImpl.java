package com.springboot.xmind.service.impl;

import com.springboot.xmind.dao.XmindDao;
import com.springboot.xmind.entity.Xmind;
import com.springboot.xmind.service.XmindService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("xmindService")
public class XmindServiceImpl implements XmindService {

	@Autowired
	XmindDao xmindDao;

	@Override
	public Page<Xmind> findAll(String topic, String lang, Pageable pageable) {
		return xmindDao.findAll(Specifications.where(getWhereClause(topic, lang)),pageable);
	}

	@Override
	public Page<Xmind> findAllByHot(Pageable pageable, String language, String key) {
		return xmindDao.findAll(pageable);
	}

	@Override
	public Page<Xmind> findLast(Pageable pageable) {
		return xmindDao.findAll(pageable);
	}

	@Override
	public void save(Xmind xmind) {
		xmindDao.save(xmind);
	}

	@Override
	public Page<Xmind> findByName(String name, Pageable pageable) {
		return xmindDao.findByUsername(name, pageable);
	}

	@Override
	public List<Xmind> findByName(String name) {
		return xmindDao.findByUsername(name);
	}

	public Specification<Xmind> getWhereClause(final String topic, final String lang) {
		return new Specification<Xmind>() {
			@Override
			public Predicate toPredicate(Root<Xmind> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(StringUtils.isNotEmpty(topic)){
					predicate.getExpressions().add(
							cb.like(r.<String>get("topic"), "%" + StringUtils.trim(topic) + "%")
					);
				}
				if(StringUtils.isNotEmpty(lang)){
					predicate.getExpressions().add(
							cb.like(r.<String>get("lang"),"%" + StringUtils.trim(lang) + "%")
					);
				}
				return predicate;
			}
		};

	}

}
