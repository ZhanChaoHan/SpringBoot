package com.jachs.jpa.ntm;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.ntm.ExamRepository;
import com.jachs.jpa.dao.ntm.ExamineeRepository;
import com.jachs.jpa.entity.ntm.Exam;
import com.jachs.jpa.entity.ntm.Examinee;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class TestExam {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamineeRepository examineeRepository;
    
    
    @Test
    public void testAddOne() {
        Exam exam=new Exam();
        
        exam.setExamId ( 1 );
        exam.setExamName ( "语文" );
        exam.setScore ( 65.6 );
        
        examRepository.save ( exam );
    }
    @Test
    public void testAddOneLinkOne() {
        Exam exam=new Exam();
        
        exam.setExamId ( 1 );
        exam.setExamName ( "语文" );
        exam.setScore ( 65.6 );
        
        Set<Examinee>examineeSet=new HashSet<> ();
        Examinee examinee=new Examinee();
        examinee.setExamineeId ( 1 );
        examinee.setExamineeName ( "马保国" );
        
        examineeRepository.save ( examinee );
        examineeSet.add ( examinee );
        
        exam.setExaminees ( examineeSet );
        examRepository.save ( exam );
    }
    
}
