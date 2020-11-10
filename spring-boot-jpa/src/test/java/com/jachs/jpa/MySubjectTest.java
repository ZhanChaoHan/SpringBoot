package com.jachs.jpa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

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
    public void testAdd () {
        List<MyStudent> myStudentList = myStudentRepository.findAll ();
        MySubject ms = new MySubject ();
        ms.setSubjectName ( "语文" );

        ms.setMyStudentList ( myStudentList );

        mySubjectRepository.save ( ms );
    }
    /***
     * 随机生成课程数据
     */
    @Test
    public void testAddRandomName () {
        RandomStringUtils rsu = new RandomStringUtils ();
        for ( int k = 0 ; k < 20 ; k++ ) {
            List<MyStudent> myStudentList = myStudentRepository.findAll ();
            MySubject ms = new MySubject ();
            ms.setSubjectName ( rsu.random ( 2, 0x4e00, 0x9fa5, false, false ) );

            ms.setMyStudentList ( myStudentList );

            mySubjectRepository.save ( ms );
        }
    }
    
    /***
     * 简单分页
     */
    @Test
    public void testPage() {
        Pageable pageable = PageRequest.of(2,5);
        Page<MySubject> pmList=mySubjectRepository.findAll ( pageable );
        for ( MySubject mySubject : pmList ) {
            System.out.println ( mySubject.getSubjectName () );
        }
    }
    /***
     * 排序分页
     */
    @Test
    public void testPageSort() {
        List<Order>arr=new ArrayList<Sort.Order>();
        arr.add ( new Order ( Sort.Direction.DESC, "subject_name" ) );//一直找不到字段
        Sort sort =Sort.by ( arr );//创建排序字段
        
        Pageable pageable = PageRequest.of(0,5,sort);
        Page<MySubject> pmList=mySubjectRepository.findAll ( pageable );
        for ( MySubject mySubject : pmList ) {
            System.out.println ( mySubject.getSubjectName () );
        }
    }
}
