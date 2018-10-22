package com.springboot.xmind.service;

import com.springboot.xmind.entity.Xmind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    /**
     * @auther: zhangyingqi
     * @date: 11:39 2018/10/22 
     * @param: [name]
     * @return: java.util.List<com.springboot.xmind.entity.Xmind>
     * @Description: 通过username得到其全部发布
     */
    List<Xmind> findByName(String name);

    /**
     * @auther: zhangyingqi
     * @date: 11:40 2018/10/22 
     * @param: [name, pageable]
     * @return: org.springframework.data.domain.Page<com.springboot.xmind.entity.Xmind>
     * @Description: 同上，加入分页
     */
    Page<Xmind> findByName(String name, Pageable pageable);

    /**
     * @auther: zhangyingqi
     * @date: 11:41 2018/10/22 
     * @param: [name, pageable]
     * @return: org.springframework.data.domain.Page<com.springboot.xmind.entity.Xmind>
     * @Description: 获取下载量top100的用户
     */
    Page<Xmind> getDownTop100(Pageable pageable);
}
