package com.jachs.jpa.oto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.oto.StudentCardRepository;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class StudentCardTest {
    @Autowired
    private StudentCardRepository studentCardRepository;
    
    @Test
    public void testAdd() {
        
    }
}
