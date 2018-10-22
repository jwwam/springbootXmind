package com.springboot.xmind.dao;

import com.springboot.xmind.entity.Xmind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface XmindDao extends PagingAndSortingRepository<Xmind, Long>,JpaSpecificationExecutor<Xmind>,JpaRepository<Xmind,Long> {

/*    @Query("from Label l where l.labelName = ?1")
    Page<Xmind> findByLabelName(String labelName, Pageable pageable);

    @Query("from Label l where l.id = ?1")
    Xmind findById(String id);

    @Modifying
    @Query("UPDATE Label SET labelName=?1,labelDes=?2 WHERE id = ?3")
    int updateLabel(String labelName, String labelDes, String id);*/

    Page<Xmind> findByUsername(String username, Pageable pageable);

    @Query("from Xmind x where x.username = ?1")
    List<Xmind> findByUsername(String username);
    //@Query("select distinct(icbd.reason) from ICBReportData icbd where icbd.registerNo = ?1")
    //select userName, SUM(downloads) downloads from t_xmind group by username order by downloads desc
    @Query("select userName, SUM(downloads) from Xmind x group by x.username")
    Page<Xmind> getDownTop100(Pageable pageable);

}

