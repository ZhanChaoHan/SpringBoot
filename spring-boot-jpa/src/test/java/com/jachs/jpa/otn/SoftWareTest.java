package com.jachs.jpa.otn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.otn.SoftWareRepository;

/**
 * @author zhanchaohan
 * oTn,n就没什么好测的了
 */
@SpringBootTest
public class SoftWareTest {
    @Autowired
    private SoftWareRepository softWareRepository;
    
    
}
