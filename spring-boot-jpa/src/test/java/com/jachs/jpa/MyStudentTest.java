package com.jachs.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private MyStudentRepository myStudentRepository;
    
    @Test
    public void testAdd() {
        MyClass mc=new MyClass();
        mc.setClassName ( "小学一年级" );
        mc.setClassType ( 10 );
        
        MyStudent ms01=new MyStudent();
        
        ms01.setMyClass(mc);
        
        ms01.setStudentAge ( 2 );
        ms01.setStudentGender ( 0 );
        ms01.setStudentName ( "张2岁" );
        
        myStudentRepository.save ( ms01 );
    }
    
}
