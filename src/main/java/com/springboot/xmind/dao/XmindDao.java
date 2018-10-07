package com.springboot.xmind.dao;

import com.springboot.xmind.entity.Xmind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface XmindDao extends PagingAndSortingRepository<Xmind, Long>,JpaSpecificationExecutor<Xmind>,JpaRepository<Xmind,Long> {

/*    @Query("from Label l where l.labelName = ?1")
    Page<Xmind> findByLabelName(String labelName, Pageable pageable);

    @Query("from Label l where l.id = ?1")
    Xmind findById(String id);

    @Modifying
    @Query("UPDATE Label SET labelName=?1,labelDes=?2 WHERE id = ?3")
    int updateLabel(String labelName, String labelDes, String id);*/

}

