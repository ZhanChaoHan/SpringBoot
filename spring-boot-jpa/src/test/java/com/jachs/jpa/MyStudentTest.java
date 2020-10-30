package com.jachs.jpa;

import java.util.Optional;

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
        Optional<MyClass> mc=myClassRepository.findById ( 2 );
        
        MyStudent ms01=new MyStudent();
        
        ms01.setMyClass(mc.get ());
        
        ms01.setStudentAge ( 2 );
        ms01.setStudentGender ( 0 );
        ms01.setStudentName ( "张2岁" );
        
        myStudentRepository.save ( ms01 );
    }
    
}
