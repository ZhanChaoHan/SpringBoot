package com.jachs.jpa.oto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.oto.StudentCardRepository;
import com.jachs.jpa.dao.oto.StudentRepository;
import com.jachs.jpa.entity.oto.Student;
import com.jachs.jpa.entity.oto.StudentCard;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCardRepository studentCardRepository;
    
    //单存
    @Test
    public void saveAStudent() {
        Student s=new Student();
        s.setStudentId ( "sOne" );
        s.setStudentName ( "马保国" );
        s.setStudentAge ( 69 );
        
        studentRepository.save ( s );
    }
    //关联存储
    @Test
    public void saveAStudentAndCard() {
        Student s=new Student();
        s.setStudentId ( "sOne" );
        s.setStudentName ( "马保国" );
        s.setStudentAge ( 69 );
        
        StudentCard sc=new StudentCard();
        sc.setCardId ( "sOneC" );
        sc.setCardMonery ( 5L );
        sc.setCardType ( "老年卡" );
        
        
        s.setStudentCard ( sc );
        studentRepository.save ( s );
    }
    //删除主表从表数据一起删了
    @Test
    public void deleteStuend() {
        studentRepository.delete ( studentRepository.findById ( "sOne" ).get () );
    }
   //删除从表数据,删不掉因为有外键
    @Test
    public void deleteStuendCard() {
        studentCardRepository.delete ( studentCardRepository.findById ( "sOneC" ).get () );
    }
}
