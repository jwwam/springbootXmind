package com.springboot.xmind.service;

import com.springboot.xmind.entity.Xmind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface XmindService {

    /**
     * @auther: zhangyingqi
     * @date: 11:37 2018/10/12 
     * @param: [pageable, language, key]
     * @return: org.springframework.data.domain.Page<com.springboot.xmind.entity.Xmind>
     * @Description: 按照条件查询所有
     */
    Page<Xmind> findAll(String key, String language, Pageable pageable);

    /**
     * @auther: zhangyingqi
     * @date: 11:37 2018/10/12 
     * @param: [pageable, language, key]
     * @return: org.springframework.data.domain.Page<com.springboot.xmind.entity.Xmind>
     * @Description: 查询所有by热度
     */
    Page<Xmind> findAllByHot(Pageable pageable, String language, String key);

    /**
     * @auther: zhangyingqi
     * @date: 10:27 2018/10/16
     * @param: [pageable]
     * @return: org.springframework.data.domain.Page<com.springboot.xmind.entity.Xmind>
     * @Description: 获取最后一条录入的数据
     */
    Page<Xmind> findLast(Pageable pageable);

    void save(Xmind xmind);
}
