package com.jachs.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.MyStudentRepository;
import com.jachs.jpa.dao.MySubjectRepository;
import com.jachs.jpa.entity.MyStudent;
import com.jachs.jpa.entity.MySubject;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class MySubjectTest {
    @Autowired
    private MyStudentRepository myStudentRepository;
    @Autowired
    private MySubjectRepository mySubjectRepository;
    
    @Test
    public void testAdd() {
    	List<MyStudent> myStudentList=myStudentRepository.findAll();
    	MySubject ms=new MySubject();
    	ms.setSubjectName("语文");
    	
    	ms.setMyStudentList(myStudentList);
    	
    	
    	mySubjectRepository.save(ms);
    }
    
}
