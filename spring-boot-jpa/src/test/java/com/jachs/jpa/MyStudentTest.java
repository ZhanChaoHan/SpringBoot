package com.jachs.jpa;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.MyClassRepository;
import com.jachs.jpa.dao.MyStudentRepository;
import com.jachs.jpa.entity.MyClass;
import com.jachs.jpa.entity.MyStudent;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class MyStudentTest {
    @Autowired
    private MyClassRepository myClassRepository;
    @Autowired
    private MyStudentRepository myStudentRepository;
    
    @Test
    public void testAdd() {
    	MyClass mc=new MyClass();
    	mc.setClassName("二年级");
    	mc.setClassType(2);
    	
        MyStudent ms01=new MyStudent();
        ms01.setStudentAge ( 2 );
        ms01.setStudentGender ( 0 );
        ms01.setStudentName ( "张2岁" );
        ms01.setMyClass(mc);
        
        MyStudent ms02=new MyStudent();
        ms02.setStudentAge ( 3 );
        ms02.setStudentGender ( 0 );
        ms02.setStudentName ( "张3岁" );
        ms02.setMyClass(mc);
        
        
        myClassRepository.save(mc);
        myStudentRepository.save ( ms01 );
        myStudentRepository.save ( ms02 );
    }
    
}
