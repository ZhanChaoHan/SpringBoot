package com.jachs.jpa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.jachs.jpa.entity.MySubject;



/**
 * @author zhanchaohan
 * 
 */
public interface MySubjectRepository extends CrudRepository<MySubject,Integer>{
    Iterable<MySubject> findAll(Sort sort);
    
    Page<MySubject> findAll(Pageable pageable);
    
}
