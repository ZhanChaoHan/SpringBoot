package com.jachs.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.MyClassRepository;
import com.jachs.jpa.entity.MyClass;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class MyClassTest {
    @Autowired
    private MyClassRepository myClassRepository;
    
    @Test
    public void testAdd() {
        MyClass mc=new MyClass();
        mc.setClassName ( "小学一年级" );
        mc.setClassType ( 10 );
        myClassRepository.save ( mc );
    }
    
}
