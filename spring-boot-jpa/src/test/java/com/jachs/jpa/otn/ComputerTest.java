package com.jachs.jpa.otn;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.otn.ComputerRepository;
import com.jachs.jpa.dao.otn.SoftWareRepository;
import com.jachs.jpa.entity.otn.Computer;
import com.jachs.jpa.entity.otn.SoftWare;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class ComputerTest {
    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private SoftWareRepository softWareRepository;
    
    @Test
    public void testC() {
        Computer computer=new Computer();
        
        computer.setComputerId ( "C1" );
        computer.setComputerName ( "微软" );
        computer.setComputerPrice ( 123456L );
       
        computerRepository.save ( computer );
    }
    @Test
    public void testCA() {
        Computer computer=computerRepository.findById ( "C1" ).get ();
        
        Set<SoftWare>swSet=new HashSet<> ();
        
        SoftWare sw01=new SoftWare();
        sw01.setSoftWareId ( "01" );
        sw01.setSoftWareName ( "wechar" );
//        sw01.setComputer ( computer );
        swSet.add ( sw01 );
        
        
        SoftWare sw02=new SoftWare();
        sw02.setSoftWareId ( "02" );
        sw02.setSoftWareName ( "QQ" );
//        sw02.setComputer ( computer );
        swSet.add ( sw02 );
        
        computer.setSoftWares ( swSet );
        computerRepository.save ( computer );
        
    }
    @Test
    public void testCAR() {
        Computer computer=computerRepository.findById ( "C1" ).get ();
        
        Set<SoftWare>swSet=new HashSet<> ();
        
        SoftWare sw01=new SoftWare();
        sw01.setSoftWareId ( "01" );
        sw01.setSoftWareName ( "wechar" );
        sw01.setComputer ( computer );
        swSet.add ( sw01 );
        
        
        SoftWare sw02=new SoftWare();
        sw02.setSoftWareId ( "02" );
        sw02.setSoftWareName ( "QQ" );
        sw02.setComputer ( computer );
        swSet.add ( sw02 );
        
        computer.setSoftWares ( swSet );
        
        softWareRepository.saveAll ( swSet );
    }
}
