package com.jachs.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jachs.jpa.entity.MyStudent;



/**
 * @author zhanchaohan
 * 
 */
public interface MyStudentRepository extends JpaRepository<MyStudent,String>{

}
