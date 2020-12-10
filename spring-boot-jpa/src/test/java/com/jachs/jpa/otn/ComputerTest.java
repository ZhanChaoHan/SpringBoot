package com.jachs.jpa.otn;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.jpa.dao.otn.ComputerRepository;
import com.jachs.jpa.entity.otn.Computer;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class ComputerTest {
    @Autowired
    private ComputerRepository computerRepository;
    
    //单条添加
    @Test
    public void testAdd() {
        Computer computer=new Computer();
        
        computer.setComputerId ( "C1" );
        computer.setComputerName ( "微软" );
        computer.setComputerPrice ( 123456L );
       
        
        computerRepository.save ( computer );
    }
    //多条添加
    @Test
    public void testAddMany() {
        Set<Computer>cList=new HashSet<> ();
        
        Computer computer=new Computer();
        
        computer.setComputerId ( "C1" );
        computer.setComputerName ( "微软" );
        computer.setComputerPrice ( 123456L );
        cList.add ( computer );
        
        Computer computer1=new Computer();
        
        computer1.setComputerId ( "C2" );
        computer1.setComputerName ( "联想" );
        computer1.setComputerPrice ( 3456L );
        cList.add ( computer1 );
        
        Computer computer2=new Computer();
        
        computer2.setComputerId ( "C3" );
        computer2.setComputerName ( "神州" );
        computer2.setComputerPrice ( 79908L );
        cList.add ( computer2 );
        
        computerRepository.saveAll ( cList );
    }
}
